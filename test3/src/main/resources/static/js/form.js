let layout;
let fileForm;

const init = () => {

    createLayout();

    settingForm();

}


const createLayout = () => {
    layout = new dhx.Layout("layout", {
        //셀 사이에 선도, 공백도 넣지 않는다.
        type: "none",

        cols: [
            {
                id: "mainContent",
                css: "mainContent",
                width:1100,
                height:700,

                rows: [
                    {
                        id: "fileForm",
                        name : "fileForm",
                        css: "fileForm",
                        width: 500,
                        height: 120,
                        padding:0,
                    },
                    {
                        id: "responseDiv",
                        css: "responseDiv",
                        width: 1000,
                        height: 500,
                        padding: 0,
                    },
                ]
            },

        ]
    });
}


//레이아웃 만들어놓고
// 레이아웃 내부에 id 가 form 인 부분을 선언했다면
const settingForm = () => {

    fileForm = new dhx.Form(null, {
        //사방으로 border가 있다
        css: "dhx_widget--bordered fileForm",
        padding: 0,
        rows: [
            {
                cols: [

                    {
                        //DHTMLX 8의 Form 컴포넌트에서 공식적으로 지원하는 type
                        //input, textarea, checkbox, radio, select, button, label, spacer, html
                        //file은 없습니다.
                        //그래서 직접 html 타입으로 넣는 것이 유일한 대안
                        //DHTMLX 8에서 type: "html"을 쓸 때는 width, height 속성이 적용되지 않음.
                        id: "dbFile",
                        type: "html",
                        //html: `<input type="file" id="dbFile" class="dbFile">`,
                        
                        //html: '<input ...>'은 DHTMLX 내부에서 내부 DOM으로 주입되지만,
                        //일부 구조(예: <input type="file">)는 브라우저 보안 정책 혹은 DHTMLX 렌더링 구조 문제로 인해 안됨
                        //하지만 여기다가 id 넣어도 지정되지 않고
                        //data-cell-id="dbFile" 만 생긴다.
                        html: `<div id="dbFilePlaceholder"></div>`,  // 빈 div만 넣음
                        css : "dbFile",
                        //cols에 부모태그의 넓이 전체가 분배되기 때문에 어쩔수없이 여기도 width 지정
                        width: 290,
                        padding: 0,

                    },
                    {
                        //name => 폼 데이터를 다룰 때 데이터 객체의 key 역할
                        //form.getValue();
                        //=> { username: "홍길동" }
                        name: "submitButton",
                        id: "submitButton",
                        type: "button",
                        // value: language.button_add,
                        submit: false,
                        //DHTMLX가 만든 외부 래퍼 div에만 붙습니다.
                        //하지만 실제 크기를 정하는 건 내부의 .dhx_button 엘리먼트
                        css: "marginLeft10 submitButton",
                        width: 190,
                        padding: 0,
                        text: "파일올리기"
                    }
                ]
            }
        ]
    });

    //클릭한 버튼의 이름(또는 이름이 지정되지 않은 경우 ID)
    fileForm.events.on("click", (id, event) => {
        switch (id) {
            //이걸 클릭했을때
            case "submitButton":
                console.log("파일 올리기 버튼 클릭");
                //여기다가 클릭후 ajax 실행시켜서 동적으로 grid 생성해야함
                ajaxSubmit(event);
                break;
        }
    });

    //이렇게 해당 레이아웃 cell 에 다시 attach 까지 해주면 화면에 나올거에요
    //attach()는 내부적으로 새롭게 렌더를 트리거하지만 ready 이벤트를 자동으로 다시 내보내지 않음
    layout.getCell("fileForm").attach(fileForm);


    //.events.on("ready", ...)는 DHTMLX에서 공식적으로 컴포넌트가 화면에 완전히 렌더된 후를 알려주는 신뢰성 높은 방식
    //afterAdd 이벤트는 새 필드가 동적으로 추가될 때만 동작
    // fileForm.events.on("afterAdd", (id, event) => {
    //     // const placeholder = document.getElementById("dbFilePlaceholder");
    //     // if (placeholder) {
    //     //     const input = document.createElement("input");
    //     //     input.type = "file";
    //     //     input.id = "dbFile";
    //     //     input.className = "dbFile";
    //     //     placeholder.appendChild(input);
    //     //     console.log("들어왔다");
    //     // }
    //
    //     const inputHtml = `<input type="file" id="dbFile" class="dbFile">`;
    //     fileForm.getCell("dbFile").attachHTML(inputHtml);
    // });

    //DHTMLX는 화면이 완전히 그려질 때까지 기다리는 공식적인 Promise API를 제공
    // Form attach → Layout attach → DOM 실제 렌더링까지 모두 끝나야 원하는 엘리먼트를 안전하게 조작
    dhx.awaitRedraw().then(() => {
        const placeholder = document.querySelector('[data-cell-id="dbFile"]')
        if (placeholder) {
            const input = document.createElement("input");
            input.type = "file";
            input.id = "dbFile";
            input.className = "dbFileInput";
            placeholder.innerHTML = '';
            placeholder.appendChild(input);
            console.log("파일 input 삽입됨");
        } else {
            console.warn("dbFile이 아직 없음");
        }
    });

}


/**
 * 업로드한 파일을 Ajax로 보낸 후 DB table에 추가
 * @param event 이벤트 객체
 */
const ajaxSubmit = (event) => {
    event.preventDefault();

    const formData = new FormData();
    const file = document.getElementById("dbFile").files[0];
    formData.append('file', file);

    $.ajax({
        url: '/upload/insertTable',
        method: 'POST',
        data: formData,              // 일반 데이터나 FormData 객체
        processData: false,          // 파일 업로드 시 false
        contentType: false,          // 파일 업로드 시 false
        success: function(response) {
            console.log('성공:', response);
            console.log('리턴 타입 : '+typeof response);

            makeResultHtml(response);

        },
        error: function(xhr, status, error) {
            console.error('실패:', error);
        }
    });
}

/**
 * 전체성공/각 줄별로 실패를 HTML로 동적 생성
 * @param response ResponseBase 응답객체
 */
const makeResultHtml = (response) => {
    let responseSpace = document.querySelector('[data-cell-id="responseDiv"]');
    let responseSpaceText = '';

    /*
        boolean 타입 필드에 대해 getter는 isXxx() 형식이 권장되며,
        getXxx()가 아니라 isXxx()가 자동으로 인식
        따라서 Jackson 등 직렬화 라이브러리는 getter 이름에서 is 접두사를 빼고 필드명을 normal로 추론
     */

    if (response.normal == false) { // 비정상적 플래그면
        switch (response.content.exceptionCode) {
            case "FAIL_FILE_OPEN":  //파일읽기 리더가 실패했을때
                console.log("파일 열기 실패");
                responseSpaceText += `<h1>파일 읽기 실패하였습니다.</h1>`;
                responseSpaceText += `<h1>다시 재시도 해주세요.</h1>`;
                responseSpace.innerHTML = responseSpaceText;
                break;

            case "WRONG_FILE_EXTENSION":  //사용자가 잘못된 파일 확장자를 올렸을때
                console.log("사용자가 잘못된 파일 올림");
                responseSpaceText += `<h1>잘못된 파일 형식입니다.</h1>`;
                responseSpaceText += `<h1>.dbfile 형식으로 올려주세요.</h1>`;
                responseSpace.innerHTML = responseSpaceText;
                break;

            default:
        }

    } else { // 정상적인 플래그면
        if (response.content.totalCount == response.content.successCount) {  //전체 성공
            responseSpaceText += `<h3>▼ 전체 성공</h3>`;
            responseSpaceText += `<h3>레코드 건수  ${response.content.successCount}건 입력 성공</h3>`;
            responseSpaceText += `<button id="findAllButton">조회버튼</button>`;

            //조회하기 결과 표가 붙을 자리
            responseSpaceText += `<div id="result"></div>`;

            responseSpace.innerHTML = responseSpaceText;
            document.getElementById("findAllButton").classList.add("findAllButton");
            document.getElementById("findAllButton").addEventListener('click',function(event){
                ajaxSelectAll(event);
            });

        } else { //일부 실패
            responseSpaceText += `<h3>▼ 전체/일부 실패</h3>`;
            responseSpaceText += `<h3>성공  ${response.content.successCount}건, 실패 ${response.content.totalCount - response.content.successCount}건 </h3>`;
            responseSpaceText += `<h3>실패한 라인번호와 텍스트</h3> `;
            response.content.userResultDTOList.forEach(userResultDTO => {
                if (userResultDTO.successFlag == false) {
                    responseSpaceText += `<h5>라인번호 : ${userResultDTO.failLine}, 실패한 텍스트 : ${userResultDTO.failText}</h5> `;
                    //원인들이 많을 경우 분리
                    // const exceptionMessageParts = userResultDTO.exceptionMessage.split(",");
                    // for(const exceptionMessagePart of exceptionMessageParts){
                    //     responseSpaceText += `<h5>원인 : ${exceptionMessagePart}</h5> `;
                    // }

                }
            });
            responseSpace.innerHTML = responseSpaceText;
        }

    }
}


