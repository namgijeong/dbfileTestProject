let layout;
let fileForm;
let responseDivForm;
let trueResult;
let falseResult;

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
                height:900,

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
                        width: 500,
                        height: 200,
                        padding: 0,
                        //평소에는 보이지 않게
                        hidden:true,
                    },
                    {
                        id: "falseResult",
                        css: "result",
                        width: 510,
                        height: 460,
                        padding: 0,
                        hidden:true,
                    },
                    {
                        id: "trueResult",
                        css: "result",
                        width: 810,
                        height: 450,
                        padding: 0,
                        hidden:true,
                    },
                    {
                        id:	"pagingArea",
                        name: "pagingArea",
                        css: "pagingArea",
                        height: 50,
                        padding:0,
                    }

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
    layout.getCell("responseDiv").show();

    let responseSpaceText1 = '';
    let responseSpaceText2 = '';
    let responseSpaceText3 = '';

    let showFindAllButton = false;
    let showFalseResult = false;
    let showTrueResult = false;
    let showResponseSpaceText3 = false;
    //성공했을때 표냐, 실패했을때 표냐
    let resultGrid = null;

    /*
        boolean 타입 필드에 대해 getter는 isXxx() 형식이 권장되며,
        getXxx()가 아니라 isXxx()가 자동으로 인식
        따라서 Jackson 등 직렬화 라이브러리는 getter 이름에서 is 접두사를 빼고 필드명을 normal로 추론
     */

    if (response.normal == false) { // 비정상적 플래그면
        switch (response.content.exceptionCode) {
            case "FAIL_FILE_OPEN":  //파일읽기 리더가 실패했을때
                console.log("파일 열기 실패");
                responseSpaceText1 = "파일 읽기 실패하였습니다.";
                responseSpaceText2 = "다시 재시도 해주세요.";
                break;

            case "WRONG_FILE_EXTENSION":  //사용자가 잘못된 파일 확장자를 올렸을때
                console.log("사용자가 잘못된 파일 올림");
                responseSpaceText1 = "잘못된 파일 형식입니다.";
                responseSpaceText2 = ".dbfile 형식으로 올려주세요.";
                break;

            default:
        }

    } else { // 정상적인 플래그면
        if (response.content.totalCount === response.content.successCount) {  //전체 성공
            responseSpaceText1 = "▼ 전체 성공";
            responseSpaceText2 = "레코드 건수 " + response.content.successCount + "건 입력 성공";

            //버튼 붙이기 하단
            showFindAllButton = true;
            
            //회원 전체 목록 출력
            showTrueResult = true;

            //조회하기 결과 표가 붙을 자리
            //responseSpaceText += `<div id="result"></div>`;

            const dataset =[];
            const config = {
                //id 속성은 컬럼명과 JSON key를 연결하는 역할
                //hidden: true로 설정된 컬럼은 그리드에 표시는 안 되지만, grid.data.getItem()에서는 정상적으로 조회
                //columns는 "화면에 어떤 필드를 어떤 방식으로 표시할지" 정의
                //data는 실제로 **"어떤 값을 각 행(row)에 넣을지"**를 담는 별도의 객체
                //1. grid 생성시 포함
                // data: [...]
                // 방법 2: 나중에 넣기
                //grid.data.parse([...]);
                //data의 자료형에 자바스크립트  Date() 객체를 사용가능
                columns: [
                    //id: "loginId" → 각 데이터 행(row)의 loginId 필드 값을 이 컬럼에 표시
                    //header: [{ text: "Login ID" }] → 헤더 셀에 "Login ID" 라는 텍스트 표시
                    { id: "failLine", header: [{ text:"failLine"}], width:100 },
                    { id: "failText", header: [{ text: "failText"}], width:400 },
                ],

                //사방으로 border가 있다
                css: "dhx_widget--bordered table",

                //그리드의 열을 그리드 크기에 맞게 조정
                //단, 이 역시 부모 layout이 공간을 제한하면 무용지물이 될 수 있음.
                autoWidth: true,
                //열 머리글을 클릭했을 때 정렬이 활성화되는지 여부를 정의
                sortable: false,
                // 열의 모든 도구 설명을 활성화/비활성화
                tooltip: false
            };
            falseResult = new dhx.Grid(null, config);

            response.content.userResultDTOList.forEach(userResultDTO => {
                if (userResultDTO.successFlag == false) {

                    let data = {failLine : userResultDTO.failLine, failText : userResultDTO.failText};
                    dataset.push(userResultDTO);
                    console.log("data : "+data.failLine);
                    console.log("data : "+data.failText);

                }
            });

            console.log("dataset : "+dataset);
            falseResult.data.parse(dataset);
            resultGrid = falseResult;



        } else { //일부 실패
            //실패 블록 보여주기
            showFalseResult = true;
            //실패문구 추가로 보여주기
            showResponseSpaceText3 = true;

            responseSpaceText1 = "▼ 전체/일부 실패</h3>";
            responseSpaceText2 = "성공  "+ response.content.successCount+"건, 실패  "+ (response.content.totalCount - response.content.successCount) + "건 ";
            responseSpaceText3 = "실패한 라인번호와 텍스트";

            const dataset =[];
            const config = {
                //id 속성은 컬럼명과 JSON key를 연결하는 역할
                //hidden: true로 설정된 컬럼은 그리드에 표시는 안 되지만, grid.data.getItem()에서는 정상적으로 조회
                //columns는 "화면에 어떤 필드를 어떤 방식으로 표시할지" 정의
                //data는 실제로 **"어떤 값을 각 행(row)에 넣을지"**를 담는 별도의 객체
                //1. grid 생성시 포함
                // data: [...]
                // 방법 2: 나중에 넣기
                //grid.data.parse([...]);
                //data의 자료형에 자바스크립트  Date() 객체를 사용가능
                columns: [
                    //id: "loginId" → 각 데이터 행(row)의 loginId 필드 값을 이 컬럼에 표시
                    //header: [{ text: "Login ID" }] → 헤더 셀에 "Login ID" 라는 텍스트 표시
                    { id: "failLine", header: [{ text:"failLine"}], width:100 },
                    { id: "failText", header: [{ text: "failText"}], width:400 },
                ],

                //사방으로 border가 있다
                css: "dhx_widget--bordered table",

                //그리드의 열을 그리드 크기에 맞게 조정
                //단, 이 역시 부모 layout이 공간을 제한하면 무용지물이 될 수 있음.
                autoWidth: true,
                //열 머리글을 클릭했을 때 정렬이 활성화되는지 여부를 정의
                sortable: false,
                // 열의 모든 도구 설명을 활성화/비활성화
                tooltip: false
            };
            falseResult = new dhx.Grid(null, config);

            response.content.userResultDTOList.forEach(userResultDTO => {
                if (userResultDTO.successFlag == false) {

                    let data = {failLine : userResultDTO.failLine, failText : userResultDTO.failText};
                    dataset.push(userResultDTO);
                    console.log("data : "+data.failLine);
                    console.log("data : "+data.failText);

                }
            });

            console.log("dataset : "+dataset);
            falseResult.data.parse(dataset);
            resultGrid = falseResult;

        }
    }

    responseDivForm = new dhx.Form(null, {
        //사방으로 border가 있다
        css: "dhx_widget--bordered responseDiv",
        padding: 0,
        rows: [

            {
                id: "responseTextDiv1",
                type: "text",
                value: responseSpaceText1,
                css : "responseTextDiv",
                width: 480,
                height: 50,
                padding: 0,
            },
            {
                id: "responseTextDiv2",
                type: "text",

                value: responseSpaceText2,
                css : "responseTextDiv",
                width: 480,
                height: 50,
                padding: 0,
            },
            {
                id: "responseTextDiv3",
                type: "text",
                value: responseSpaceText3,
                css : "responseTextDiv",
                hidden: true,
                width: 480,
                height: 50,
                padding: 0,
            },
            {
                name: "findAllButton",
                id: "findAllButton",
                type: "button",
                submit: false,
                css: "findAllButton",
                hidden: true,
                width: 190,
                height: 70,
                padding: 0,
                text: "조회하기"
            },
            {

            }

        ]


    });

    //모두 맞을경우 - 조회하기 버튼 보여주기
    if (showFindAllButton) {
        console.log("findallbutton show");

        dhx.awaitRedraw().then(() => {
            responseDivForm.getItem("findAllButton").show();
            layout.getCell("showTrueResult").show();

            //조회하기 버튼 이벤트 달기
            responseDivForm.events.on("click", (id, event) => {
                switch (id) {
                    //이걸 클릭했을때
                    case "findAllButton":
                        console.log("파일 올리기 버튼 클릭");
                        //여기다가 클릭후 ajax 실행시켜서 동적으로 grid 생성해야함
                        ajaxSubmit(event);
                        break;
                }
            });

        });

    }

    //하나라도 실패했을때 실패 출력버튼 보여주기
    if (showFalseResult) {
        console.log("showFalseResult show");

        dhx.awaitRedraw().then(() => {
            responseDivForm.getItem("responseTextDiv3").show();
            layout.getCell("falseResult").show();
            layout.getCell("falseResult").attach(falseResult);

        });
    }

    //실패든, 성공이든 페이징을 해야한다.
    settingPagination(resultGrid);

    //이렇게 해당 레이아웃 cell 에 다시 attach 까지 해주면 화면에 나올거에요
    //attach()는 내부적으로 새롭게 렌더를 트리거하지만 ready 이벤트를 자동으로 다시 내보내지 않음
    layout.getCell("responseDiv").attach(responseDivForm);


}


const settingPagination = (resultGrid) => {
    // dhx.Pagination은 내부적으로 grid.data (즉, 전체 데이터 소스)를 참조해서:
    // 전체 데이터 개수를 파악하고,
    // pageSize에 따라 자동으로 페이지 나눔을 계산하며,
    // 현재 페이지에 표시할 데이터를 자동으로 계산해서 그리드에 보여줍니다.
    pagination = new dhx.Pagination(null, {

        //grid의 data => 위에서 parse로 설정한 새로운 데이터에 접근
        data: resultGrid.data,

        //page => The index of the initial page set in the pagination
        //pageSize => 선택 사항. 관련 위젯의 페이지당 표시되는 항목 수
        pageSize: 10,
    });

    layout.getCell("pagingArea").attach(pagination);
    //setPage => 관련 위젯에 활성 페이지를 설정
    //0부터 1페이지
    pagination.setPage(0);

    //활성 페이지 변경
    //change: (index: number, previousIndex: number) => void;
    //index: number 새로 활성화된 페이지의 인덱스
    //previousIndex: number- 이전에 활성화된 페이지의 인덱스
    pagination.events.on("change", (index, previousIndex) => {
        //findSmsUsage(index);
        console.log("현재 페이지: "+index);
    });
}


/**
 * 전체성공시 조회하기 버튼을 클릭하면 작동
 * db table에 있는 모든 user 정보 가져옴
 * @param event 이벤트 객체
 */
const ajaxSelectAll = (event) => {
    event.preventDefault();

    $.ajax({
        url: '/upload/selectFullUsers',
        method: 'POST',
        success: function(response) {
            //makeTableHtml(response);
        },
        error: function(xhr, status, error) {
            console.error('실패:', error);
        }
    });

}


/**
 * DB table에 있는 레코드 각각을 HTML로 동적 생성
 * @param response UserDTO List
 */
const makeTableHtml = (response) => {
    layout.getCell("result").show();

    let resultSpace = document.getElementById('result');
    let resultSpaceText = '';

    const config = {
        //id 속성은 컬럼명과 JSON key를 연결하는 역할
        //hidden: true로 설정된 컬럼은 그리드에 표시는 안 되지만, grid.data.getItem()에서는 정상적으로 조회
        //columns는 "화면에 어떤 필드를 어떤 방식으로 표시할지" 정의
        //data는 실제로 **"어떤 값을 각 행(row)에 넣을지"**를 담는 별도의 객체
        //1. grid 생성시 포함
        // data: [...]
        // 방법 2: 나중에 넣기
        //grid.data.parse([...]);
        //data의 자료형에 자바스크립트  Date() 객체를 사용가능
        columns: [
            //id: "loginId" → 각 데이터 행(row)의 loginId 필드 값을 이 컬럼에 표시
            //header: [{ text: "Login ID" }] → 헤더 셀에 "Login ID" 라는 텍스트 표시
            { id: "id", header: [{ text:"ID"}], width:100 },
            { id: "pwd", header: [{ text: "PWD"}], width:100 },
            { id: "name", header: [{ text: "NAME"}], width:100 },
            { id: "level", header: [{ text: "LEVEL" }], width:100 },
            { id: "desc", header: [{ text: "DESC" }],width:100 },
            { id: "reg_date", header: [{ text: "REGDATE" }], width:300 },
        ],

        data: dataset,

        //사방으로 border가 있다
        css: "dhx_widget--bordered table",


        //그리드의 열을 그리드 크기에 맞게 조정
        //단, 이 역시 부모 layout이 공간을 제한하면 무용지물이 될 수 있음.
        autoWidth: true,
        //열 머리글을 클릭했을 때 정렬이 활성화되는지 여부를 정의
        sortable: false,
        // 열의 모든 도구 설명을 활성화/비활성화
        tooltip: false
    };

    // if(userResults) {
    //     //레이아웃 인스턴스를 제거하고 점유된 리소스를 해제합니다.
    //     userResults.destructor();
    // }

    result = new dhx.Grid(null, config);

    layout.getCell("result").attach(result);

    // resultSpaceText += '<table>';
    //
    // resultSpaceText += `<tr>`;
    // resultSpaceText += `<th> ID </th>`;
    // resultSpaceText += `<th> PWD </th>`;
    // resultSpaceText += `<th> NAME </th>`;
    // resultSpaceText += `<th> LEVEL </th>`;
    // resultSpaceText += `<th> DESC </th>`;
    // resultSpaceText += `<th> REG_DATE </th>`;
    // resultSpaceText += `</tr>`;

    if (response.content.length === 0) {
        //resultSpaceText += `</table>`;
        resultSpaceText += `<h1>회원 정보가 비어 있습니다.</h1>`;
    } else {
        response.content.forEach(userDTO => {
            console.log("배열 하나: " + userDTO+'\n');
            console.log('내용 : '+ userDTO.id+'\n');
            console.log('내용: '+ userDTO.pwd+'\n');
            console.log('내용: '+ userDTO.name+'\n');
            console.log('내용: '+ userDTO.level+'\n');
            console.log('내용: '+ userDTO.desc+'\n');

            let descText = makeNullToBlank(userDTO.desc);

            //@JsonProperty("reg_date") 때문에 snake 명으로 바뀐다.
            console.log('내용: '+ userDTO.reg_date+'\n');

            const date = parseDate(userDTO.reg_date);
            let regDateText = makeRegDate(date);
            //
            // resultSpaceText += `<tr>`;
            // resultSpaceText += `<td> ${userDTO.id} </td>`;
            // resultSpaceText += `<td> ${userDTO.pwd} </td>`;
            // resultSpaceText += `<td> ${userDTO.name} </td>`;
            // resultSpaceText += `<td> ${userDTO.level} </td>`;
            // resultSpaceText += `<td> ${descText} </td>`;
            // resultSpaceText += `<td> ${regDateText} </td>`;
            // resultSpaceText += `</tr>`;



        });

        result.data.parse(response.content);

        resultSpaceText += `</table>` ;
    }

    //resultSpace.innerHTML = resultSpaceText;

}



