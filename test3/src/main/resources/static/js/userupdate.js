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
                height:1200,
                padding:0,

                cols: [
                    {
                        id: "updateUserForm",
                        name : "updateUserForm",
                        css : "updateUserForm",
                        width: 500,
                        height: 1150,
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
                //회원가입 에러 문구표시
                id:"updateCondition7",
                css:"updateCondition",
                width: 450,
                height: 150,
                padding:0,
                //hidden:true,

                rows: [
                    {

                        id:"updateErrorSection1",
                        name:"updateErrorSection1",
                        css:"conditionArea",
                        //type:"container",
                        type: "text",
                        hidden:true,

                        width:400,
                        height:50,
                        padding:0,
                    },
                    {

                        id:"updateErrorSection2",
                        name:"updateErrorSection2",
                        css:"conditionArea",
                        //type:"container",
                        type: "text",
                        hidden:true,

                        width:400,
                        height:50,
                        padding:0,
                    },
                    {

                        id:"updateErrorSection3",
                        name:"updateErrorSection3",
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
                id : "updateCondition8",
                css : "updateCondition",
                width : 450,
                height : 50,
                padding:0,

                cols:[
                    {
                        id: "conditionName8",
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

    //id => 클릭한 버튼의 이름(또는 이름이 지정되지 않은 경우 ID)
    updateUserForm.events.on("click", (id, event) => {
        switch (id) {
            //이걸 클릭했을때
            case "updateButton":
                console.log("업데이트 버튼 클릭");
                //여기다가 클릭후 ajax 실행시켜서 문구표출
                checkUpdateAjax(event);
                break;

            case "goListButton":
                console.log("돌아가기 버튼 클릭");
                window.history.back();
                break;

        }
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


const checkUpdateAjax = (event) => {
    let id = updateUserForm.getItem("id").getValue();
    let pwd = updateUserForm.getItem("pwd").getValue();
    let name = updateUserForm.getItem("name").getValue();
    let level = updateUserForm.getItem("level").getValue();
    let desc = updateUserForm.getItem("desc").getValue();
    let regDate = updateCalendar.getValue();
    regDate = plusZeroTime(regDate);

    const checkUpdateData = {id:id, pwd:pwd, name:name, level:level, desc:desc, reg_date:regDate};
    console.log(checkUpdateData);

    fetch("/register/update_check", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(checkUpdateData)
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
                    text: "업데이트가 완료 되었습니다.",
                    buttonsAlignment: "center",
                    buttons: ["ok"],

                }).then(function(answer){

                    window.location.href = "/user/user_list/page?pageNumber=1";
                });


            } else { //오류 메시지 출력
                //insertUserForm.getItem("insertCondition6").show();

                console.log(checkAnswer.content.exceptionMessage);

                let formItemId = "updateErrorSection";
                let formItemIdFull = '';

                //다시 출력할때는 초기화하자
                for(let i= 1; i <= 3 ; i++) {
                    let formItemIdFull='';
                    formItemIdFull = formItemId+i;
                    updateUserForm.getItem(formItemIdFull).hide();
                }
                checkAnswer.content.exceptionMessage.forEach((processResultDTO,index) => {
                    let formItemIdFull = '';
                    formItemIdFull = formItemId+(index+1);
                    console.log(formItemIdFull)
                    updateUserForm.getItem(formItemIdFull).show();
                    updateUserForm.getItem(formItemIdFull).setValue(processResultDTO.errorMessage);

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