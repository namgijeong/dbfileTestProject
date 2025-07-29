var layout;
var searchForm;
var SearchCalendar;
var userResults;

const init = () => {
    createLayout();

    settingForm();
    settingCalendar();

    settingGrid();

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
                        height: 500,
                        padding:0,
                    },
                    {
                        id:	"pagination",
                        height: 50,
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
                                id : "id1",
                                name : "id1",
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
                                id : "name1",
                                name : "name1",
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
                                id : "level1",
                                name : "level1",
                                css: "input",
                                type : "combo",
                                data : [
                                    {id: "id_1", value :"A"},
                                    {id: "id_2", value :"B"},
                                    {id: "id_3", value :"C"},
                                    {id: "id_4", value :"D"},
                                    {id: "id_5", value :"E"},
                                    {id: "id_6", value :"F"},
                                    {id: "id_7", value :"G"},
                                    {id: "id_8", value :"H"},
                                    {id: "id_9", value :"I"},
                                    {id: "id_10", value :"J"},
                                    {id: "id_11", value :"K"},
                                    {id: "id_12", value :"L"},
                                    {id: "id_13", value :"M"},
                                    {id: "id_14", value :"N"},
                                    {id: "id_15", value :"O"},
                                    {id: "id_16", value :"P"},
                                    {id: "id_17", value :"Q"},
                                    {id: "id_18", value :"R"},
                                    {id: "id_19", value :"S"},
                                    {id: "id_20", value :"T"},
                                    {id: "id_21", value :"U"},
                                    {id: "id_22", value :"V"},
                                    {id: "id_23", value :"W"},
                                    {id: "id_24", value :"X"},
                                    {id: "id_25", value :"Y"},
                                    {id: "id_26", value :"Z"},

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
                                id : "desc1",
                                name : "desc1",
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
                                id : "regDate1",
                                name : "regDate1",
                                //A control to attach HTML code or the DHTMLX widgets.
                                type : "container",
                                css: "input",
                                width: 200,
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
                break;
        }
    });

    //콤보박스 level의 값이 바뀌면
    searchForm.getItem("level1").events.on("change", function(ids) {
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
        width : 200,
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
    searchForm.getItem("regDate1").attach(SearchCalendar);
}


const settingGrid = () => {
    const dataset =[
        {
            // 행 ID를 명시적으로 지정 (생략 시 자동 생성됨)
            id: "NJJH",
            pwd: "7a5BmFhHd",
            name: "임꺽정",
            level: "C",
            desc: "의적",
            regDate: "2025년 10월 24일 10시 27분 00초",
        },
        {
            // 행 ID를 명시적으로 지정 (생략 시 자동 생성됨)
            id: "HAMG",
            pwd: "QejbX9iq",
            name: "홍길동",
            level: "C",
            desc: " ",
            regDate: "2025년 10월 12일 03시 27분 00초",
        },
        {
            // 행 ID를 명시적으로 지정 (생략 시 자동 생성됨)
            id: "QEAISV",
            pwd: "QejbX9iq",
            name: "홍길동",
            level: "C",
            desc: " ",
            regDate: "2025년 10월 12일 03시 27분 00초",
        },
        {
            // 행 ID를 명시적으로 지정 (생략 시 자동 생성됨)
            id: "OIJQGA",
            pwd: "QejbX9iq",
            name: "홍길동",
            level: "C",
            desc: " ",
            regDate: "2025년 10월 12일 03시 27분 00초",
        },
        {
            // 행 ID를 명시적으로 지정 (생략 시 자동 생성됨)
            id: "DYSXY",
            pwd: "QejbX9iq",
            name: "홍길동",
            level: "C",
            desc: " ",
            regDate: "2025년 10월 12일 03시 27분 00초",
        },
        {
            // 행 ID를 명시적으로 지정 (생략 시 자동 생성됨)
            id: "KVQV",
            pwd: "QejbX9iq",
            name: "홍길동",
            level: "C",
            desc: " ",
            regDate: "2025년 10월 12일 03시 27분 00초",
        },
        {
            // 행 ID를 명시적으로 지정 (생략 시 자동 생성됨)
            id: "BHLZ",
            pwd: "QejbX9iq",
            name: "홍길동",
            level: "C",
            desc: " ",
            regDate: "2025년 10월 12일 03시 27분 00초",
        },
        {
            // 행 ID를 명시적으로 지정 (생략 시 자동 생성됨)
            id: "WVOHZ",
            pwd: "QejbX9iq",
            name: "홍길동",
            level: "C",
            desc: " ",
            regDate: "2025년 10월 12일 03시 27분 00초",
        },
        {
            // 행 ID를 명시적으로 지정 (생략 시 자동 생성됨)
            id: "HHUU",
            pwd: "QejbX9iq",
            name: "홍길동",
            level: "C",
            desc: " ",
            regDate: "2025년 10월 12일 03시 27분 00초",
        },
        {
            // 행 ID를 명시적으로 지정 (생략 시 자동 생성됨)
            id: "HHUV",
            pwd: "QejbX9iq",
            name: "홍길동",
            level: "C",
            desc: " ",
            regDate: "2025년 10월 12일 03시 27분 00초",
        },

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
            { id: "id", header: [{ text:"ID"}], width:100 },
            { id: "pwd", header: [{ text: "PWD"}], width:100 },
            { id: "name", header: [{ text: "NAME"}], width:100 },
            { id: "level", header: [{ text: "LEVEL" }], width:100 },
            { id: "desc", header: [{ text: "DESC" }],width:100 },
            { id: "regDate", header: [{ text: "REGDATE" }], width:300 },
        ],

        data: dataset,

        //사방으로 border가 있다
        css: "dhx_widget--bordered",

        //layout에 정의된 width, height 최우선 (레이아웃이 부모니까)
        //grid 자체의 width, height =>  레이아웃이 사이즈를 안 정할 때만 반영
        // height: 200,
        // width: 300,

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

    layout.getCell("userResults").attach(userResults);


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

    });
}

