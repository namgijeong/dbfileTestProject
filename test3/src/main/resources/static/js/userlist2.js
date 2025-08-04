let layout;
let searchForm;
let SearchCalendar;
let userResults;
let currentPage = 1;
let totalPage = 1;
let isSearching = false;

const init = () => {
    createLayout();

    settingForm();
    settingCalendar();

    settingGrid();
    settingPagination();

    awaitRedraw();

}

const createLayout = () => {
    layout = new dhx.Layout("layout", {
        //셀 사이에 선도, 공백도 넣지 않는다.
        type: "none",
        css : "layout",
        padding:0,

        cols: [
            {
                id: "mainContent",
                css: "mainContent",
                width:900,
                height:1200,
                padding:0,

                rows: [
                    {
                        id: "searchArea",
                        name : "searchArea",
                        css : "searchArea",
                        width: 470,
                        height: 560,
                        padding:0,
                    },
                    {
                        id: "userResults",
                        name : "userResults",
                        css: "userResults",
                        width: 810,
                        height: 450,
                        padding:0,
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

    searchForm = new dhx.Form(null, {
        //사방으로 border가 있다
        css: "dhx_widget--bordered",
        padding: 0,
        rows: [
            {
                id : "searchCondition1",
                css : "searchCondition",
                width : 450,
                height : 50,
                padding:0,

                cols:[
                    {
                        id: "conditionName1",
                        css : "conditionName",
                        width : 100,
                        height : 50,
                        padding:0,
                    },
                    {
                        id: "conditionValue1",
                        css : "conditionValue",
                        width:300,
                        height: 50,
                        padding:0,

                        cols : [
                            {
                                id : "id",
                                name : "id",
                                css: "input",
                                type : "input",
                                inputType : "text",
                                placeholder : "id",
                                width: 200,
                                height: 30,
                                padding : 0,
                            }

                        ]
                    }


                ]

            },

            {
                id : "searchCondition2",
                css : "searchCondition",
                width : 450,
                height : 50,
                padding:0,

                cols:[
                    {
                        id: "conditionName2",
                        css : "conditionName",
                        width : 100,
                        height : 50,
                        padding:0,
                    },
                    {
                        id: "conditionValue2",
                        css : "conditionValue",
                        width:300,
                        height: 50,
                        padding:0,

                        cols : [
                            {
                                id : "name",
                                name : "name",
                                css: "input",
                                type : "input",
                                inputType : "text",
                                placeholder : "name",
                                width: 200,
                                height: 30,
                                padding : 0,
                            }

                        ]
                    },



                ]

            },

            {
                id : "searchCondition3",
                css : "searchCondition",
                width : 450,
                height : 50,
                padding:0,

                cols:[
                    {
                        id: "conditionName3",
                        css : "conditionName",
                        width : 100,
                        height : 50,
                        padding:0,
                    },
                    {
                        id: "conditionValue3",
                        css : "conditionValue",
                        width:300,
                        height: 50,
                        padding:0,

                        cols : [
                            {
                                id : "level",
                                name : "level",
                                css: "input",
                                type : "combo",
                                data : [
                                    {id: "A", value :"A"},
                                    {id: "B", value :"B"},
                                    {id: "C", value :"C"},
                                    {id: "D", value :"D"},
                                    {id: "E", value :"E"},
                                    {id: "F", value :"F"},
                                    {id: "G", value :"G"},
                                    {id: "H", value :"H"},
                                    {id: "I", value :"I"},
                                    {id: "J", value :"J"},
                                    {id: "K", value :"K"},
                                    {id: "L", value :"L"},
                                    {id: "M", value :"M"},
                                    {id: "N", value :"N"},
                                    {id: "O", value :"O"},
                                    {id: "P", value :"P"},
                                    {id: "Q", value :"Q"},
                                    {id: "R", value :"R"},
                                    {id: "S", value :"S"},
                                    {id: "T", value :"T"},
                                    {id: "U", value :"U"},
                                    {id: "V", value :"V"},
                                    {id: "W", value :"W"},
                                    {id: "X", value :"X"},
                                    {id: "Y", value :"Y"},
                                    {id: "Z", value :"Z"},

                                ],

                                placeholder : "level",
                                width: 200,
                                height: 30,
                                padding : 0,
                            }

                        ]
                    },



                ]

            },

            {
                id : "searchCondition4",
                css : "searchCondition",
                width : 450,
                height : 50,
                padding:0,

                cols:[
                    {
                        id: "conditionName4",
                        css : "conditionName",
                        width : 100,
                        height : 50,
                        padding:0,
                    },
                    {
                        id: "conditionValue4",
                        css : "conditionValue",
                        width:300,
                        height: 50,
                        padding:0,

                        cols : [
                            {
                                id : "desc",
                                name : "desc",
                                css: "input",
                                type : "input",
                                inputType : "text",
                                placeholder : "desc",
                                width: 200,
                                height: 30,
                                padding : 0,
                            }

                        ]
                    },



                ]

            },

            {
                id : "searchCondition5",
                css : "searchCondition",
                width : 450,
                height : 300,
                padding:0,

                cols:[
                    {
                        id: "conditionName5",
                        css : "conditionName",
                        width : 100,
                        height : 50,
                        padding:0,
                    },
                    {
                        id: "conditionValue5",
                        css : "conditionValue",
                        width:300,
                        height: 300,
                        padding:0,

                        cols : [
                            {
                                id : "regDate",
                                name : "regDate",
                                //A control to attach HTML code or the DHTMLX widgets.
                                type : "container",
                                css: "input",
                                width: 250,
                                height: 300,
                                padding : 0,
                            }

                        ]
                    },



                ]

            },

            {
                id : "searchCondition6",
                css : "searchCondition",
                width : 450,
                height : 50,
                padding:0,

                cols:[
                    {
                        id: "conditionName6",
                        css : "conditionName",
                        width : 100,
                        height : 50,
                        padding:0,

                        cols : [
                            {
                                id : "searchButton",
                                name : "searchButton",
                                css: "searchButton",
                                
                                type : "button",
                                submit: false,
                                
                                width: 100,
                                height: 40,
                                padding : 0,
                                text:"검색"
                            }

                        ]
                    },


                ]

            },

        ]
    });

    //id => 클릭한 버튼의 이름(또는 이름이 지정되지 않은 경우 ID)
    searchForm.events.on("click", (id, event) => {
        switch (id) {
            //이걸 클릭했을때
            case "searchButton":
                console.log("찾기 버튼 클릭");
                //여기다가 클릭후 ajax 실행시켜서 동적으로 grid 생성해야함
                searchAjaxFirst(event);
                break;
        }
    });

    //콤보박스 level의 값이 바뀌면
    searchForm.getItem("level").events.on("change", function(ids) {
        console.log("change", ids);
    });


    //이렇게 해당 레이아웃 cell 에 다시 attach 까지 해주면 화면에 나올거에요
    //attach()는 내부적으로 새롭게 렌더를 트리거하지만 ready 이벤트를 자동으로 다시 내보내지 않음
    layout.getCell("searchArea").attach(searchForm);

}

const settingCalendar = () => {
    //getWidget() => returns the widget attached to Window
    //let regDateInput = searchForm.getItem("regDate").getWidget();
    //let regDateInput = searchForm.getItem("regDate");
    //console.log("regDateInput : "+regDateInput);

    SearchCalendar = new dhx.Calendar(null, {
        css: "dhx_widget--bordered",
        // 날짜 범위 선택을 활성화
        range: true,
        dateFormat:"%Y-%m-%d",
        //현재 표시된 날짜를 기준으로 이전/다음 달의 날짜를 숨김
        thisMonthOnly:true,
        width : 250,
    });


    //change: (date: Date, oldDate: Date, byClick: boolean) => void;
    //date: Date- 새로 선택한 날짜
    //oldDate: Date- 이전에 선택한 날짜
    //byClick: boolean- 날짜 클릭으로 인해 변경이 발생했는지( true) 또는 API 호출로 인해 변경이 발생했는지( false) 를 정의
    SearchCalendar.events.on("change",function(date, oldDate, byClick){
        console.log("Change selection from "+oldDate+" to "+date);
    });

    console.log("calendar 만들기 ");

    //위 dhx.Form 생성할때 내부요소에 type container로 지정해서 가능하다
    searchForm.getItem("regDate").attach(SearchCalendar);
}


/**
 * 페이징 통신 Ajax
 * 페이지 이동없이 누른 버튼 값에 따라 회원정보 페이지 내용 변경
 */
function userListPagingAjax() {
    $.ajax({
        url: '/user/userList/ajax?pageNumber=' + currentPage,
        method: 'GET',
        success: function (response) {
            console.log("response.content.buttonBlockDTO : " + response.content.buttonBlockDTO);
            console.log("response.content.userDTOList : " + response.content.userDTOList);

            totalPage = response.content.buttonBlockDTO.totalPageNumber;
            isSearching = false;

            makePagingTable(response.content.userDTOList);
            paginationButtonMode();
            viewCountChange();
            /*
                사용자 입장에서 눈속임용
                페이지 리로드 없이, 서버 통신없이 url 이름만 바꾸기(뒤로가기 기록 남기지 않음)
                state : 그 URL과 연결된 상태 데이터
                title : 페이지 제목
                url : 주소창에 표시될 URL
            */
            window.history.replaceState(null, "", "/user/userList/ajax?pageNumber=" + currentPage);
        },
        error: function (xhr, status, error) {
            console.error('실패:', error);
        }
    });
}


//검색 버튼을 눌러서 ajax
const searchAjaxFirst = (event) => {
    event.preventDefault();

    let id = searchForm.getItem("id").getValue();
    let name = searchForm.getItem("name").getValue();
    //returns IDs of options which are currently selected in the Combo control
    let level = searchForm.getItem("level").getValue();
    let desc = searchForm.getItem("desc").getValue();
    let regDate = SearchCalendar.getValue();
    let startRegDate = regDate[0];
    let endRegDate = regDate[1];

    console.log("id : "+id);
    console.log("name : "+name);
    console.log("level : "+level);
    console.log("startRegDate : "+startRegDate);
    console.log("endRegDate : "+endRegDate);


    //만약 사용자가 입력을 안했으면 null로 바꾸기
    //level은 dhtmlx8 콤보박스에서 잘못된 형식으로 입력하면 그냥 공백으로 만든다.
    //regDate는 dhtmlx8 calendar에서 클릭안한 상태라면 undefined가 반환된다.
    id = makeBlankToNull(id);
    name = makeBlankToNull(name);
    level = makeBlankToNull(level);
    desc = makeBlankToNull(desc);
    if (startRegDate === undefined || endRegDate === undefined) {
        startRegDate = null;
        endRegDate = null;
    } else { //localdatetime으로 매핑하기 위해 00:00:00 붙인다.
        startRegDate = plusZeroTime(startRegDate);
        endRegDate = plusZeroTime(endRegDate);
    }

    console.log("formated id: "+id);
    console.log("formated name: "+name);
    console.log("formated desc: "+desc);
    console.log("formated startRegDate: "+startRegDate);
    console.log("formated endRegDate: "+endRegDate);

    const searchData = {
        id: id,
        name: name,
        level: level,
        desc: desc,
        start_reg_date: startRegDate,
        end_reg_date: endRegDate,
        page_number : 1,
    };

    fetch("/user/search/userList/ajax", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(searchData)
    }) .then(async response => {
        /*
            그냥 ok로 하고 body 객체 안에서 flag 검사해서 아이디,비번 오류 메시지 출력
            응답(response) 본문을 JSON으로 파싱해서 Promise로 반환
         */
        if (response.ok) {
            const searchAnswer  = await response.json();

            console.log("검색 조회 성공")
            totalPage = searchAnswer.content.buttonBlockDTO.totalPageNumber;
            currentPage = 1;

            console.log("currentPage : "+currentPage);
            isSearching = true;

            makePagingTable(searchAnswer.content.userDTOList);
            paginationButtonMode();
            viewCountChange();

            /*
                사용자 입장에서 눈속임용
                페이지 리로드 없이, 서버 통신없이 url 이름만 바꾸기(뒤로가기 기록 남기지 않음)
                state : 그 URL과 연결된 상태 데이터
                title : 페이지 제목
                url : 주소창에 표시될 URL
            */
            window.history.replaceState(null, "", "/user/search/userList/ajax?pageNumber=" + currentPage);

        } else {
            const errorMessage = await response.json();
            console.log("errorMessage : "+errorMessage);

        }
    }).catch(error => {
        console.log("error : "+error);
    })

}


//검색 버튼을 눌러서 첫번째 ajax 후, 페이징 ajax
const searchAjaxAfterFirst = (event) => {

    let id = searchForm.getItem("id").getValue();
    let name = searchForm.getItem("name").getValue();
    //returns IDs of options which are currently selected in the Combo control
    let level = searchForm.getItem("level").getValue();
    let desc = searchForm.getItem("desc").getValue();
    let regDate = SearchCalendar.getValue();
    let startRegDate = regDate[0];
    let endRegDate = regDate[1];

    console.log("id : "+id);
    console.log("name : "+name);
    console.log("level : "+level);
    console.log("startRegDate : "+startRegDate);
    console.log("endRegDate : "+endRegDate);


    //만약 사용자가 입력을 안했으면 null로 바꾸기
    //level은 dhtmlx8 콤보박스에서 잘못된 형식으로 입력하면 그냥 공백으로 만든다.
    //regDate는 dhtmlx8 calendar에서 클릭안한 상태라면 undefined가 반환된다.
    id = makeBlankToNull(id);
    name = makeBlankToNull(name);
    level = makeBlankToNull(level);
    desc = makeBlankToNull(desc);
    if (startRegDate === undefined || endRegDate === undefined) {
        startRegDate = null;
        endRegDate = null;
    } else { //localdatetime으로 매핑하기 위해 00:00:00 붙인다.
        startRegDate = plusZeroTime(startRegDate);
        endRegDate = plusZeroTime(endRegDate);
    }

    console.log("formated id: "+id);
    console.log("formated name: "+name);
    console.log("formated desc: "+desc);
    console.log("formated startRegDate: "+startRegDate);
    console.log("formated endRegDate: "+endRegDate);

    const searchData = {
        id: id,
        name: name,
        level: level,
        desc: desc,
        start_reg_date: startRegDate,
        end_reg_date: endRegDate,
        page_number : currentPage,
    };

    fetch("/user/search/userList/ajax", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(searchData)
    }) .then(async response => {
        /*
            그냥 ok로 하고 body 객체 안에서 flag 검사해서 아이디,비번 오류 메시지 출력
            응답(response) 본문을 JSON으로 파싱해서 Promise로 반환
         */
        if (response.ok) {
            const searchAnswer  = await response.json();

            console.log("검색 조회 성공")
            totalPage = searchAnswer.content.buttonBlockDTO.totalPageNumber;

            console.log("currentPage : "+currentPage);
            isSearching = true;

            makePagingTable(searchAnswer.content.userDTOList);
            paginationButtonMode();
            viewCountChange();

            /*
              사용자 입장에서 눈속임용
              페이지 리로드 없이, 서버 통신없이 url 이름만 바꾸기(뒤로가기 기록 남기지 않음)
              state : 그 URL과 연결된 상태 데이터
              title : 페이지 제목
              url : 주소창에 표시될 URL
          */
            window.history.replaceState(null, "", "/user/search/userList/ajax?pageNumber=" + currentPage);

        } else {
            const errorMessage = await response.json();
            console.log("errorMessage : "+errorMessage);

        }
    }).catch(error => {
        console.log("error : "+error);
    })

}


const settingGrid = () => {
    const dataset =[];


    //thymeleaf 변수
    userDTOList.forEach(userDTO => {
        console.log("userDTO.id : "+userDTO.id);

        //thymeleaf를 사용했기 때문에
        //${} 안의 객체를 JSON으로 자동 변환
        let originalRegDate =userDTO.reg_date;
        console.log("originalRegDate : "+originalRegDate);
        let completeRegDate = makeLocalDateTimeToString(originalRegDate);

        let descText = userDTO.desc;
        let cleanText = makeNullToBlank(descText);

        const data = {id : userDTO.id, pwd: userDTO.pwd, name:userDTO.name, desc:cleanText, reg_date:completeRegDate }
        dataset.push(data);
    });


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

    userResults = new dhx.Grid(null, config);

    //load()데이터 수집 메서드를 통해 Grid에 데이터를 로드 하고 lazyDataProxy이 메서드의 매개변수로 전달
    // userResults.data.load(lazyDataProxy);
    layout.getCell("userResults").attach(userResults);


}

const settingPagination = () => {
    // dhx.Pagination은 내부적으로 grid.data (즉, 전체 데이터 소스)를 참조해서:
    // 전체 데이터 개수를 파악하고,
    // pageSize에 따라 자동으로 페이지 나눔을 계산하며,
    // 현재 페이지에 표시할 데이터를 자동으로 계산해서 그리드에 보여줍니다.
    pagination = new dhx.Pagination(null, {

        //grid의 data => 위에서 parse로 설정한 새로운 데이터에 접근
        data: userResults.data,

        //page => The index of the initial page set in the pagination
        //pageSize => 선택 사항. 관련 위젯의 페이지당 표시되는 항목 수
        pageSize: 10,

    });

    layout.getCell("pagingArea").attach(pagination);
    //setPage => 관련 위젯에 활성 페이지를 설정
    //0부터 1페이지
    //눈속임용으로 번호를 바꿀거긴하지만 원래 1/1페이지므로 0 설정
    pagination.setPage(0);
    totalPage = buttonBlockDTO.totalPageNumber;

    console.log("pagination.config : ",pagination.config);

    //활성 페이지 변경
    //change: (index: number, previousIndex: number) => void;
    //index: number 새로 활성화된 페이지의 인덱스
    //previousIndex: number- 이전에 활성화된 페이지의 인덱스
    pagination.events.on("change", (index, previousIndex) => {
        console.log("현재 페이지: "+index);
    });


}

const makePagingTable = (userDTOList) => {
    for (userDTO of userDTOList) {
        let reg_date;
        reg_date = parseDate(userDTO.reg_date);
        reg_date = makeRegDate(reg_date);
        userDTO.reg_date = reg_date;
    }

    userResults.data.parse(userDTOList);
    makePagingButton(userDTOList);
}
const makePagingButton = (userDTOList) => {
    pagination.data.parse(userDTOList);

}


const awaitRedraw = () => {
    //DHTMLX는 화면이 완전히 그려질 때까지 기다리는 공식적인 Promise API를 제공
    // Form attach → Layout attach → DOM 실제 렌더링까지 모두 끝나야 원하는 엘리먼트를 안전하게 조작
    dhx.awaitRedraw().then(() => {

        const idDivText = document.querySelector('[data-cell-id="conditionName1"] > div');
        if(idDivText) {
            idDivText.textContent = "ID";
            idDivText.classList.add("itemDiv");
        }

        const nameDivText = document.querySelector('[data-cell-id="conditionName2"] > div');
        if(nameDivText) {
            nameDivText.textContent = "NAME";
            nameDivText.classList.add("itemDiv");
        }

        const levelDivText = document.querySelector('[data-cell-id="conditionName3"] > div');
        if(levelDivText) {
            levelDivText.textContent = "LEVEL";
            levelDivText.classList.add("itemDiv");
        }

        const descDivText = document.querySelector('[data-cell-id="conditionName4"] > div');
        if(descDivText) {
            descDivText.textContent = "DESC";
            descDivText.classList.add("itemDiv");
        }

        const regDateDivText = document.querySelector('[data-cell-id="conditionName5"] > div');
        if(regDateDivText) {
            regDateDivText.textContent = "RegDate";
            regDateDivText.classList.add("itemDiv");
        }

        paginationButtonMode();

        viewCountChange();

    });

}

//기본 회원정보 페이징
//화살표 누른것에 따라 ajax 통신하면서 버튼 텍스트를 바꿔준다.
const userListPaginationButtonClick = (event) =>{
    console.log("클릭됨");
    const clickedButton = event.target.dataset.dhxId;

    switch (clickedButton) {
        case "first":
            console.log("가장 왼쪽 버튼 클릭됨");
            currentPage = 1;
            break;

        case "previous":
            console.log("왼쪽 버튼 클릭됨");
            if ((currentPage - 1) > 0){
                currentPage = currentPage - 1;
            }
            break;

        case "next":
            console.log("오른쪽 버튼 클릭됨");
            if ((currentPage + 1) <=  totalPage){
                currentPage = currentPage + 1;
            }
            break;

        case "last":
            console.log("가장 오른쪽 버튼 클릭됨");
            currentPage = totalPage;
            break;

        default:
    }

    userListPagingAjax();

}

//검색 회원정보 페이징
//화살표 누른것에 따라 ajax 통신하면서 버튼 텍스트를 바꿔준다.
const searchPaginationButtonClick = (event) =>{
    console.log("클릭됨");
    const clickedButton = event.target.dataset.dhxId;

    switch (clickedButton) {
        case "first":
            console.log("가장 왼쪽 버튼 클릭됨");
            currentPage = 1;
            break;

        case "previous":
            console.log("왼쪽 버튼 클릭됨");
            if ((currentPage - 1) > 0){
                currentPage = currentPage - 1;
            }
            break;

        case "next":
            console.log("오른쪽 버튼 클릭됨");
            if ((currentPage + 1) <=  totalPage){
                currentPage = currentPage + 1;
            }
            break;

        case "last":
            console.log("가장 오른쪽 버튼 클릭됨");
            currentPage = totalPage;
            break;

        default:
    }

    searchAjaxAfterFirst();

}


//페이지네이션 버튼이 찾기 모드냐, 일반 모드냐에 따라 이벤트 연결 설정
const paginationButtonMode = () => {
    //addeventlistener가 중복 등록되서 클릭 여러번 되는것 방지
    document.querySelector(".dhx_widget .dhx_pagination").removeEventListener("click", userListPaginationButtonClick);
    document.querySelector(".dhx_widget .dhx_pagination").removeEventListener("click", searchPaginationButtonClick);

    if (isSearching === false) {
        document.querySelector(".dhx_widget .dhx_pagination").addEventListener("click", userListPaginationButtonClick);

    } else {
        document.querySelector(".dhx_widget .dhx_pagination").addEventListener("click", searchPaginationButtonClick);

    }
}


//눈속임용으로 dhtmlx8 페이지네이션 숫자 바꾸기
const viewCountChange = () => {

    //눈속임용으로 총숫자 변경
    let viewTotalCount = document.querySelector('[data-dhx-id="size"]');

    //기존 요소의 textContent는 바로 렌더링이 되지않을수도있다.
    //viewTotalCount.removeAttribute("contenteditable")
    //viewTotalCount.textContent =  "/"+ totalPage;
    //console.log("viewTotalCount.textContent : "+viewTotalCount.textContent );


    const viewTotalCountNewInput = viewTotalCount.cloneNode(true);
    viewTotalCount.parentNode.replaceChild(viewTotalCountNewInput, viewTotalCount);
    viewTotalCountNewInput.textContent =  "/"+ totalPage;

    //dhtmlx8 input 값 value에 직접적으로 못바꾸기 때문에 이렇게 바꿔야함
    const pageInput = document.querySelector('[data-dhx-id="count"]');
    const newInput = pageInput.cloneNode(true);
    pageInput.parentNode.replaceChild(newInput, pageInput);
    newInput.value = currentPage;

    console.log("viewCurrentCount.value : "+newInput.value );
}

