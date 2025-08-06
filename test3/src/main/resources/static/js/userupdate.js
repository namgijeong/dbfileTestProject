let layout;
let updateUserForm;
let updateCalendar;
const init = () => {
    createLayout();

    settingForm();
    settingCalendar();

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
                width:1000,
                height:1100,
                padding:0,

                cols: [
                    {
                        id: "updateUserForm",
                        name : "updateUserForm",
                        css : "updateUserForm",
                        width: 500,
                        height: 1000,
                        padding:0,
                    },


                ]
            },

        ]


    });
}

const settingForm = () => {
    updateUserForm = new dhx.Form(null, {
        padding: 0,
        rows: [
            {
                id: "updateCondition0",
                css : "updateCondition",
                width : 450,
                height : 50,
                padding:0,

                cols:[
                    {
                        id:"formTitle",
                        name:"formTitle",
                        css:"formTitle",
                        type: "text",
                        value: "회원수정",
                        width:300,
                        height:50,
                        padding:0,
                    }
                ]
            },
            {
                id : "updateCondition1",
                css : "updateCondition",
                width : 450,
                height : 50,
                padding:0,

                cols:[
                    {
                        id: "conditionName1",
                        css : "conditionName",
                        width : 100,
                        height : 40,
                        padding:0,
                        type: "text",
                        value: "ID",
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
                                readOnly:true,
                                width: 200,
                                height: 30,
                                padding : 0,
                            }

                        ]
                    }
                ]

            },

            {
                id : "updateCondition2",
                css : "updateCondition",
                width : 450,
                height : 50,
                padding:0,

                cols:[
                    {
                        id: "conditionName2",
                        css : "conditionName",
                        width : 100,
                        height : 40,
                        padding:0,
                        type: "text",
                        value: "PWD",
                    },
                    {
                        id: "conditionValue2",
                        css : "conditionValue",
                        width:300,
                        height: 50,
                        padding:0,

                        cols : [
                            {
                                id : "pwd",
                                name : "pwd",
                                css: "input",
                                type : "input",
                                inputType : "text",
                                placeholder : "pwd",
                                width: 200,
                                height: 30,
                                padding : 0,
                            }

                        ]
                    }


                ]

            },

            {
                id : "updateCondition3",
                css : "updateCondition",
                width : 450,
                height : 50,
                padding:0,

                cols:[
                    {
                        id: "conditionName3",
                        css : "conditionName",
                        width : 100,
                        height : 40,
                        padding:0,
                        type: "text",
                        value: "NAME",
                    },
                    {
                        id: "conditionValue3",
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
                id : "updateCondition4",
                css : "updateCondition",
                width : 450,
                height : 50,
                padding:0,

                cols:[
                    {
                        id: "conditionName4",
                        css : "conditionName",
                        width : 100,
                        height : 40,
                        padding:0,
                        type: "text",
                        value: "LEVEL",
                    },
                    {
                        id: "conditionValue4",
                        css : "conditionValue",
                        width:300,
                        height: 50,
                        padding:0,

                        cols:[
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
                id : "updateCondition5",
                css : "updateCondition",
                width : 450,
                height : 50,
                padding:0,

                cols:[
                    {
                        id: "conditionName5",
                        css : "conditionName",
                        width : 100,
                        height : 40,
                        padding:0,
                        type: "text",
                        value: "DESC",
                    },
                    {
                        id: "conditionValue5",
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
                id : "updateCondition6",
                css : "updateCondition",
                width : 450,
                height : 300,
                padding:0,

                cols:[
                    {
                        id: "conditionName6",
                        css : "conditionName",
                        width : 100,
                        height : 40,
                        padding:0,
                        type: "text",
                        value: "REGDATE",
                    },
                    {
                        id: "conditionValue6",
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
                id : "updateCondition7",
                css : "updateCondition",
                width : 450,
                height : 50,
                padding:0,

                cols:[
                    {
                        id: "conditionName7",
                        css : "conditionName",
                        width : 300,
                        height : 40,
                        padding:0,

                        cols : [
                            {
                                id : "goListButton",
                                name : "goListButton",
                                css: "goListButton",

                                type : "button",
                                submit: false,

                                width: 100,
                                height: 40,
                                padding : 0,
                                text:"돌아가기",
                            },
                            {
                                id : "updateButton",
                                name : "updateButton",
                                css: "updateButton",

                                type : "button",
                                submit: false,

                                width: 100,
                                height: 40,
                                padding : 0,
                                text:"수정완료",
                            }

                        ]
                    },


                ]

            },

        ]
    });

    layout.getCell("updateUserForm").attach(updateUserForm);
}

const settingCalendar = () => {

    updateCalendar = new dhx.Calendar(null, {
        css: "dhx_widget--bordered",
        // 날짜 범위 선택을 활성화
        range: false,
        dateFormat:"%Y-%m-%d",
        //현재 표시된 날짜를 기준으로 이전/다음 달의 날짜를 숨김
        thisMonthOnly:true,
        width : 250,
    });


    //change: (date: Date, oldDate: Date, byClick: boolean) => void;
    //date: Date- 새로 선택한 날짜
    //oldDate: Date- 이전에 선택한 날짜
    //byClick: boolean- 날짜 클릭으로 인해 변경이 발생했는지( true) 또는 API 호출로 인해 변경이 발생했는지( false) 를 정의
    updateCalendar.events.on("change",function(date, oldDate, byClick){
        console.log("Change selection from "+oldDate+" to "+date);
    });

    console.log("calendar 만들기 ");

    //위 dhx.Form 생성할때 내부요소에 type container로 지정해서 가능하다
    updateUserForm.getItem("regDate").attach(updateCalendar);
}


const awaitRedraw = () => {
    //DHTMLX는 화면이 완전히 그려질 때까지 기다리는 공식적인 Promise API를 제공
    // Form attach → Layout attach → DOM 실제 렌더링까지 모두 끝나야 원하는 엘리먼트를 안전하게 조작
    dhx.awaitRedraw().then(() => {
        //수정대상 회원을 못찾음
        if (!successFlag) {
            window.location.href = "/user/user_list/page?pageNumber=1";
        }

        updateUserForm.getItem("id").setValue(userDTO.id);
        updateUserForm.getItem("pwd").setValue(userDTO.pwd);
        updateUserForm.getItem("name").setValue(userDTO.name);
        updateUserForm.getItem("level").setValue(userDTO.level);
        updateUserForm.getItem("desc").setValue(userDTO.desc);

        //th:inline="javascript" 때문에 thymeleaf가 JSON으로 렌더링하여 JsonProperty로 접근
        console.log(userDTO.reg_date);
        //위에서 설정한 dateFormat 속성대로 넣어줘야한다.
        updateCalendar.setValue(makeLocalDateTimeToDhxCalendar(userDTO.reg_date));
        console.log("makeLocalDateTimeToDhxCalendar(userDTO.regDate) : " + makeLocalDateTimeToDhxCalendar(userDTO.reg_date));
    });

}