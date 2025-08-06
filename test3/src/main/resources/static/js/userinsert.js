let layout;
let insertUserForm;
let idCheckFlag = false;
const init = () => {
    createLayout();
    settingForm();

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
                        id:"conditionArea1",
                        css:"conditionArea",
                        width: 450,
                        height: 50,
                        padding:0,

                        cols:[
                            {
                                id : "idCheckButton",
                                name : "idCheckButton",
                                css: "idCheckButton",

                                type : "button",
                                submit: false,

                                width: 120,
                                height: 40,
                                padding : 0,
                                text:"아이디 중복",

                            },
                            {
                                //아이디가 중복되었습니다 문구 표시
                                id:"idDuplicatedSection",
                                name:"idDuplicatedSection",
                                css:"conditionArea",
                                type: "text",
                                hidden:true,

                                width:300,
                                height:50,
                            }
                        ]

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

    //id => 클릭한 버튼의 이름(또는 이름이 지정되지 않은 경우 ID)
    insertUserForm.events.on("click", (id, event) => {
        switch (id) {
            //이걸 클릭했을때
            case "idCheckButton":
                console.log("아이디 중복 버튼 클릭");
                //여기다가 클릭후 ajax 실행시켜서 문구표출
                checkIdAjax(event);
                break;
        }
    });

    layout.getCell("insertUserForm").attach(insertUserForm);
}

const checkIdAjax = (event) => {
    let id = insertUserForm.getItem("id").getValue();
    const checkIdData = {id:id};
    fetch("/user/check/duplicated_id", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(checkIdData)
    }) .then(async response => {
        /*
            그냥 ok로 하고 body 객체 안에서 flag 검사해서 아이디,비번 오류 메시지 출력
            응답(response) 본문을 JSON으로 파싱해서 Promise로 반환
         */
        if (response.ok) {
            const checkAnswer  = await response.json();

            console.log("아이디 검사 성공");
            insertUserForm.getItem("idDuplicatedSection").show();
            idCheckFlag = checkAnswer.content.successFlag;

            insertUserForm.getItem("idDuplicatedSection").setValue(checkAnswer.content.errorMessage);




        } else {
            const errorMessage = await response.json();
            console.log("errorMessage : "+errorMessage);

        }
    }).catch(error => {
        console.log("error : "+error);
    })
}

const awaitRedraw = () => {
    //DHTMLX는 화면이 완전히 그려질 때까지 기다리는 공식적인 Promise API를 제공
    // Form attach → Layout attach → DOM 실제 렌더링까지 모두 끝나야 원하는 엘리먼트를 안전하게 조작
    dhx.awaitRedraw().then(() => {

        // let insertButton = document.getElementById("insertButton");
        // insertButton.classList.add("notActiveButton");


    });

}