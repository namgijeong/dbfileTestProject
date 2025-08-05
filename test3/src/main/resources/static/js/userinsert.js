let layout;
let insertUserForm;
const init = () => {
    createLayout();
    settingForm();

    // settingForm2();
    // settingCalendar();
    //
    // settingGrid();
    // settingPagination();
    //
    // awaitRedraw();

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
                height:800,
                padding:0,

                cols: [
                    {
                        id: "insertUserForm",
                        name : "insertUserForm",
                        css : "insertUserForm",
                        width: 500,
                        height: 700,
                        padding:0,
                    },


                ]
            },

        ]


    });
}

const settingForm = () => {
    insertUserForm = new dhx.Form(null, {
        padding: 0,
        rows: [
            {
                id: "insertCondition0",
                css : "insertCondition",
                width : 450,
                height : 50,
                padding:0,

                cols:[
                    {
                        id:"formTitle",
                        name:"formTitle",
                        css:"formTitle",
                        type: "text",
                        value: "회원가입",
                        width:300,
                        height:50,
                        padding:0,
                    }
                ]
            },
            {
                id : "insertCondition1",
                css : "insertCondition",
                width : 450,
                height : 100,
                padding:0,

                rows:[
                    {
                        id:"conditionArea1",
                        css:"conditionArea",
                        width: 450,
                        height: 50,
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
                                        width: 200,
                                        height: 30,
                                        padding : 0,
                                    }

                                ]
                            }
                        ]
                    },

                    {
                        //아이디가 중복되었습니다 문구 표시
                        id:"idDuplicatedSection",
                        name:"idDuplicatedSection",
                        css:"conditionArea",
                        type: "text",
                        value: "아이디가 중복되었습니다.",
                        //hidden:true,
                    },
                ],


            },

            {
                id : "insertCondition2",
                css : "insertCondition",
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
                id : "insertCondition3",
                css : "insertCondition",
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
                id : "insertCondition4",
                css : "insertCondition",
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
                id : "insertCondition5",
                css : "insertCondition",
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
                id : "insertCondition6",
                css : "insertCondition",
                width : 450,
                height : 50,
                padding:0,

                cols:[
                    {
                        id: "conditionName6",
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
                                id : "insertButton",
                                name : "insertButton",
                                css: "insertButton",

                                type : "button",
                                submit: false,

                                width: 100,
                                height: 40,
                                padding : 0,
                                text:"추가",
                            }

                        ]
                    },


                ]

            },

        ]
    });

    layout.getCell("insertUserForm").attach(insertUserForm);
}