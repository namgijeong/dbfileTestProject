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
                width:500,
                height:700,

                rows: [
                    {
                        id: "fileForm",
                        name : "fileForm",
                        width: 500,
                        height: 120,
                        padding:0,
                    },
                    {
                        id: "responseDiv",
                        width: 1000,
                        height: 500,
                    },
                ]
            },

        ]
    });
}


//레이아웃 만들어놓고
// 레이아웃 내부에 id 가 form 인 부분을 선언했다면
const settingForm = () => {

    //const container = document.getElementById("fileForm");
    //layout.getCell("fileForm").attach(new dhx.Form( null , {
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

    // DOM 완전히 붙은 뒤 실행 보장
    // setTimeout(() => {
    //     //const placeholder = document.getElementById("dbFilePlaceholder");
    //     const placeholder = document.querySelector('[data-cell-id="dbFile"]')
    //     if (placeholder) {
    //         const input = document.createElement("input");
    //         input.type = "file";
    //         input.id = "dbFile";
    //         input.className = "dbFile";
    //         placeholder.appendChild(input);
    //         console.log("파일 input 삽입됨");
    //     } else {
    //         console.warn("dbFilePlaceholder가 아직 없음");
    //     }
    // }, 100);

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

const settingGrid = () => {
    const dataset =[
        {
            // 행 ID를 명시적으로 지정 (생략 시 자동 생성됨)
            id: "row1",
            loginId: "admin001",
            adminName : "jung",
            levelName : "상급",
            adminId : "admin111",
            tenantId : "tenant111",
            loginPasscode: "secret001",
            permitLevel : "최고수준"
        },

        {
            // 행 ID를 명시적으로 지정 (생략 시 자동 생성됨)
            id: "row2",
            loginId: "admin002",
            adminName : "jong",
            levelName : "상급",
            adminId : "admin222",
            tenantId : "tenant222",
            loginPasscode: "secret002",
            permitLevel : "최고수준"
        }
    ]
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
            { id: "loginId", header: [{ text: "로그인 아이디"}] ,width:200},
            { id: "adminName", header: [{ text: "관리자 이름"}] },
            { id: "levelName", header: [{ text: "레벨 이름" }] },
            { hidden: true, id: "adminId", header: [{ text: '' }] },
            { hidden: true, id: "tenantId", header: [{ text: '' }] },
            { hidden: true, id: "loginPasscode", header: [{ text: '' }] },
            { hidden: true, id: "permitLevel", header: [{ text: '' }] },
        ],

        data: dataset,

        //사방으로 border가 있다
        css: "dhx_widget--bordered",

        //layout에 정의된 width, height 최우선 (레이아웃이 부모니까)
        //grid 자체의 width, height =>  레이아웃이 사이즈를 안 정할 때만 반영
        height: 200,
        width: 300,

        //그리드의 열을 그리드 크기에 맞게 조정
        //단, 이 역시 부모 layout이 공간을 제한하면 무용지물이 될 수 있음.
        autoWidth: true,
        //열 머리글을 클릭했을 때 정렬이 활성화되는지 여부를 정의
        sortable: false,
        // 열의 모든 도구 설명을 활성화/비활성화
        tooltip: false
    };

    if(grid) {
        //레이아웃 인스턴스를 제거하고 점유된 리소스를 해제합니다.
        grid.destructor();
    }

    grid = new dhx.Grid(null, config);

    //grid.selection.setCell(row, column): 특정 셀(cell) 을 선택 (포커스를 줄 때 사용)
    //selection.setCell(...)은 렌더링 이후에 호출해야 정상 동작합니다. (예: grid.events.on("afterRender", ...) 안에서)

    //setCell(row?: object | string | number, column?: object | string | number, ctrlUp?: boolean, shiftUp?: boolean): void;
    //row : 선택할 셀 또는 행의 ID가 있는 객체
    //column: 열의 구성 또는 ID
    //ctrlUp : true 원하는 행이나 셀을 선택합니다. 그렇지 않으면 - false ( multiselection모드의 경우)
    //shiftUp:  true - 행 또는 셀 범위를 선택하려면 true, 그렇지 않으면 false ( multiselection모드의 경우)

    //grid.data는 내부적으로 TreeCollection 또는 DataCollection 객체
    //grid.data.getId(0) =>  첫 번째 행의 ID 가져옴
    //grid.data.getItem(rowId) => 그 행의 데이터 가져옴

    // grid.config.columns => dhx.Grid로 설정한 속성
    grid.selection.setCell(grid.data.getItem(grid.data.getId(0)), grid.config.columns[0]);

    //그리드 셀을 클릭하면
    //cellClick: (row: object, column: object, event: MouseEvent) => void;
    //event	→ 실제로는 row 객체
    //value -> 실제로는 column 객체
    // 변수명을 잘못 지었을 뿐
    // JavaScript는 인자 순서(position) 에 따라 전달
    // grid.events.on("cellClick", (event, value) => {
    //     let adminId = event.adminId;
    //     let tenantId = event.tenantId;
    //
    //     //결과적으로는 행의 어느부분을 클릭해도 event.adminId가 출력된다 => row 객체니까
    //     if (adminId) {
    //         //openEditor(event);
    //         console.log("adminId : ",adminId);
    //     }
    // });


    grid.events.on("cellClick", (row, column, event) => {
        let adminId = row.adminId;
        let tenantId = row.tenantId;

        if (adminId) {
            console.log("adminId : ",adminId);
        }

        if (tenantId) {
            console.log("tenantId : ",tenantId);
        }

        console.log("event.target.textContent : "+event.target.textContent);
    });

    layout.getCell("grid").attach(grid);
}



