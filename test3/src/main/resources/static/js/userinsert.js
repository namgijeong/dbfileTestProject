let layout;
let insertUserForm;
let registerCheckFlag = false;
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
                        id:"conditionArea2",
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
                //회원가입 에러 문구표시
                id:"insertCondition6",
                css:"insertCondition",
                width: 450,
                height: 200,
                padding:0,
                //hidden:true,

                rows: [
                    {

                        id:"registerErrorSection1",
                        name:"registerErrorSection1",
                        css:"conditionArea",
                        //type:"container",
                        type: "text",
                        hidden:true,

                        width:400,
                        height:50,
                        padding:0,
                    },
                    {

                        id:"registerErrorSection2",
                        name:"registerErrorSection2",
                        css:"conditionArea",
                        //type:"container",
                        type: "text",
                        hidden:true,

                        width:400,
                        height:50,
                        padding:0,
                    },
                    {

                        id:"registerErrorSection3",
                        name:"registerErrorSection3",
                        css:"conditionArea",
                        //type:"container",
                        type: "text",
                        hidden:true,

                        width:400,
                        height:50,
                        padding:0,
                    },
                    {

                        id:"registerErrorSection4",
                        name:"registerErrorSection4",
                        css:"conditionArea",
                        //type:"container",
                        type: "text",
                        hidden:true,

                        width:400,
                        height:50,
                        padding:0,
                    },

                ]

            },

            {
                id : "insertCondition7",
                css : "insertCondition",
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

            case "insertButton":
                console.log("추가 버튼 클릭");
                //여기다가 클릭후 ajax 실행시켜서 문구표출
                checkRegistrationAjax(event);
                break;

            case "goListButton":
                console.log("돌아가기 버튼 클릭");
                window.history.back();
                break;
        }
    });

    layout.getCell("insertUserForm").attach(insertUserForm);
}

const checkIdAjax = (event) => {
    let id = insertUserForm.getItem("id").getValue();
    const checkIdData = {id:id};
    fetch("/register/check/duplicated_id", {
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

            insertUserForm.getItem("idDuplicatedSection").setValue(checkAnswer.content.errorMessage);


        } else {
            const errorMessage = await response.json();
            console.log("errorMessage : "+errorMessage);

        }
    }).catch(error => {
        console.log("error : "+error);
    })
}


const checkRegistrationAjax = (event) => {
    let id = insertUserForm.getItem("id").getValue();
    let pwd = insertUserForm.getItem("pwd").getValue();
    let name = insertUserForm.getItem("name").getValue();
    let level = insertUserForm.getItem("level").getValue();
    let desc = insertUserForm.getItem("desc").getValue();

    const checkRegistrationData = {id:id, pwd:pwd, name:name, level:level, desc:desc};
    console.log(checkRegistrationData);

    fetch("/register/register_check", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(checkRegistrationData)
    }) .then(async response => {
        /*
            그냥 ok로 하고 body 객체 안에서 flag 검사해서 아이디,비번 오류 메시지 출력
            응답(response) 본문을 JSON으로 파싱해서 Promise로 반환
         */
        if (response.ok) {
            const checkAnswer  = await response.json();

            /*
                boolean 타입 필드에 대해 getter는 isXxx() 형식이 권장되며,
                getXxx()가 아니라 isXxx()가 자동으로 인식
                따라서 Jackson 등 직렬화 라이브러리는 getter 이름에서 is 접두사를 빼고 필드명을 normal로 추론
            */

            registerCheckFlag = checkAnswer.normal;
            if (registerCheckFlag == true) {
                //alert 창 띄우고, ok 누르면 유저리스트 페이지로
                dhx.alert({
                    header: "완료 메시지",
                    text: "회원가입이 완료 되었습니다.",
                    buttonsAlignment: "center",
                    buttons: ["ok"],

                }).then(function(answer){

                    window.location.href = "/user/user_list/page?pageNumber=1";
                });


            } else { //오류 메시지 출력
                //insertUserForm.getItem("insertCondition6").show();

                console.log(checkAnswer.content.exceptionMessage);

                let formItemId = "registerErrorSection";
                let formItemIdFull = '';

                //다시 출력할때는 초기화하자
                for(let i= 1; i <= 4 ; i++) {
                    let formItemIdFull='';
                    formItemIdFull = formItemId+i;
                    insertUserForm.getItem(formItemIdFull).hide();
                }
                checkAnswer.content.exceptionMessage.forEach((processResultDTO,index) => {
                    let formItemIdFull = '';
                    formItemIdFull = formItemId+(index+1);
                    console.log(formItemIdFull)
                    insertUserForm.getItem(formItemIdFull).show();
                    insertUserForm.getItem(formItemIdFull).setValue(processResultDTO.errorMessage);

                });


            }


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