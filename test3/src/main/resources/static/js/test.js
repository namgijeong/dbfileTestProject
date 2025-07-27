var layout;
var form;
var grid;
var pagination;
var createAdminForm;
var editForm
var createLinkList, editLinkList;
var permitLevelList = [];

const init = () => {

    createLayout();

    settingForm();

    settingGrid();
    //
    // searchAdminList();
    //
    // loadPermitLevel();
    // //관리자 계정 등록 폼
    // createForm();
    //
    // //관리자 계정 수정 폼
    // createEditForm();
    //
    // top.layout.getCell("contents").progressHide();
}

const createLayout = () => {
    layout = new dhx.Layout("layout", {
        //셀 사이에 라인을 넣는다
        type: "line",
        css: "contentsTitleWrap",
        header: top.menuTitle,
        cols: [
            {
                id: "main-content",
                // header: BUSINESS_NAME,
                rows: [
                    {
                        id: "form",
                        padding: 10,
                        width:500,
                        height: 500,
                    },
                    {
                        id: "grid",
                        padding: 10,
                        width:500,
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

    form = new dhx.Form(null, {
        //사방으로 border가 있다
        css: "dhx_widget--bordered",
        padding: 0,
        rows: [
            {
                cols: [
                    {
                        //This item takes space on the form and is used for aligning controls
                        //그냥 빈 공백
                        type: "spacer"
                    },
                    {
                        name: "addAdmin",
                        id: "addAdmin",
                        type: "button",
                        // value: language.button_add,
                        submit: false,
                        //DHTMLX가 만든 외부 래퍼 div에만 붙습니다.
                        //하지만 실제 크기를 정하는 건 내부의 .dhx_button 엘리먼트
                        css: "marginLeft10 custom-button",
                        width: 100,
                        height: 100,
                        text: "시험삼아 누르기"
                    }
                ]
            }
        ]
    });

    form.events.on("click", (id, event) => {
        switch (id) {
            //이걸 클릭했을때
            case "addAdmin":
                // createAdminForm.clear("value");
                //
                // layout.getCell("create-cell").show();
                // layout.getCell("edit-cell").hide();
                break;
        }
    });


    //이렇게 해당 레이아웃 cell 에 다시 attach 까지 해주면 화면에 나올거에요
    layout.getCell("form").attach(form);


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
            { id: "loginId", header: [{ text: "로그인 아이디"}] },
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
        //그리드의 열을 그리드 크기에 맞게 조정
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
    grid.events.on("cellClick", (event, value) => {
        let adminId = event.adminId;
        let tenantId = event.tenantId;
        
        //결과적으로는 행의 어느부분을 클릭해도 event.adminId가 출력된다
        if (adminId) {
            //openEditor(event);
            console.log("adminId : ",adminId);
        }
    });

    layout.getCell("grid").attach(grid);
}

