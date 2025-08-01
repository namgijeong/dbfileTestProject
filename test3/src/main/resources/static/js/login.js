let layout;
let loginForm;
let alertText1;
let alertText2;

const init = () => {

    createLayout();

    settingForm();

}


const createLayout = () => {
    layout = new dhx.Layout("layout", {
        //셀 사이에 선도, 공백도 넣지 않는다.
        type: "none",
        css : "layout",
        cols: [
            {
                id: "mainContent",
                css: "mainContent",
                width:600,
                height:600,
                padding: 0,

                rows: [
                    {
                        id: "loginForm",
                        name : "loginForm",
                        css : "loginForm",
                        width: 500,
                        height: 500,
                        padding:0,
                    },

                ]
            },

        ]
    });
}


//레이아웃 만들어놓고
// 레이아웃 내부에 id 가 form 인 부분을 선언했다면
const settingForm = () => {

    loginForm = new dhx.Form(null, {
        //사방으로 border가 있다
        css: "dhx_widget--bordered",
        rows: [
            //div
            {
                id: "loginFormDivId",
                width:490,
                height:160,
                padding:0,
                css : "loginFormDivDiv",

                rows: [
                    //div
                    {
                        width: 480,
                        height: 100,

                        cols:[
                            {
                                id: "idDiv",
                                width:100,
                                height: 100,
                            },
                            {
                                id: "id",
                                name : "id",
                                type : "input",
                                css: "input",
                                width:200,
                                height: 50,
                                placeholder : "id",
                            },

                        ]
                    },

                    //div
                    // {
                    //     id: "idFailDiv",
                    //     width: 480,
                    //     height: 50,
                    //     css : "loginFormDivDiv",
                    //
                    //     cols:[
                    //         {
                    //             id: "idFail",
                    //             width:400,
                    //             height: 50,
                    //         },
                    //
                    //     ]
                    //
                    // }

                ]


            },

            //div
            {
                id: "loginFormDivPwd",
                width:490,
                height:160,
                padding:0,
                css : "loginFormDivDiv",

                rows: [
                    //div
                    {
                        width: 480,
                        height: 100,

                        cols:[
                            {
                                id: "pwdDiv",
                                width:100,
                                height: 100,
                            },
                            {
                                id: "pwd",
                                name : "pwd",
                                type : "input",
                                inputType : "password",
                                css: "input",
                                width:200,
                                height: 50,
                                placeholder : "pwd",
                            },

                        ]
                    },

                    //div
                    // {
                    //     id: "pwdFailDiv",
                    //     width: 480,
                    //     height: 50,
                    //     css : "loginFormDivDiv",
                    //
                    //     cols:[
                    //         {
                    //             id: "pwdFail",
                    //             width:400,
                    //             height: 50,
                    //         },
                    //
                    //     ]
                    //
                    // }

                ]
            },

            //div
            {
                id: "loginFormDivCheck",
                width:490,
                height:150,
                padding:0,
                css : "loginFormDivDiv",

                rows: [
                    {
                        id:"loginSubmit",
                        name: "loginSubmit",
                        type: "button",
                        submit: false,
                        //DHTMLX가 만든 외부 래퍼 div에만 붙습니다.
                        //하지만 실제 크기를 정하는 건 내부의 .dhx_button 엘리먼트
                        css: "loginSubmit",
                        width: 190,
                        height:100,
                        padding: 0,
                        text: "로그인",
                    },
                    {
                        id:"checkDiv",
                        name: "checkDiv",
                        type: "text",
                        hidden: true,
                        css: "checkDiv",
                        width: 400,
                        padding: 0,
                        height: 50,

                    }
                ]
            },
        ]
    });

    //로그인 버튼 이벤트 달기
    loginForm.events.on("click", (id, event) => {
        switch (id) {
            //이걸 클릭했을때
            case "loginSubmit":
                console.log("로그인 버튼 클릭");
                //여기다가 클릭후 ajax 실행시켜서 동적으로 grid 생성해야함
                ajaxSubmit(event);
                break;
        }
    });

    //이렇게 해당 레이아웃 cell 에 다시 attach 까지 해주면 화면에 나올거에요
    //attach()는 내부적으로 새롭게 렌더를 트리거하지만 ready 이벤트를 자동으로 다시 내보내지 않음
    layout.getCell("loginForm").attach(loginForm);

    //DHTMLX는 화면이 완전히 그려질 때까지 기다리는 공식적인 Promise API를 제공
    dhx.awaitRedraw().then(() => {

        const idDivText = document.querySelector('[data-cell-id="idDiv"] > div');
        if(idDivText) {
            idDivText.textContent = "ID";

            idDivText.classList.add("itemDiv");
        }


        const pwdDivText = document.querySelector('[data-cell-id="pwdDiv"] > div');
        if(pwdDivText) {
            pwdDivText.textContent = "PWD";

            pwdDivText.classList.add("itemDiv");
        }

    });

}


/**
 *  로그인 로직을 Ajax로 처리한다.
 * @param event
 */
function ajaxSubmit(event) {
    event.preventDefault();

    const id = loginForm.getItem("id").getValue();
    const pwd = loginForm.getItem("pwd").getValue();

    const loginData = {
        id: id,
        pwd: pwd,
    };

    console.log("{\"id\":id, \"pwd\":pwd} json 문자열 출력 : "+JSON.stringify({"id":id, "pwd":pwd}));
    console.log("{id:id, pwd:pwd} json 문자열 출력 : "+JSON.stringify({id:id, pwd:pwd}));
    console.log("{id,pwd} json 문자열 출력 : "+JSON.stringify({id, pwd}));

    fetch("/login/loginCheck", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(loginData)
    }) .then(async response => {
        /*
            그냥 ok로 하고 body 객체 안에서 flag 검사해서 아이디,비번 오류 메시지 출력
            응답(response) 본문을 JSON으로 파싱해서 Promise로 반환
         */
        if (response.ok) {
            const loginAnswer  = await response.json();
            makeHtml(loginAnswer);
        } else {
            const errorMessage = await response.json();
            console.log("errorMessage : "+errorMessage);

        }
    }).catch(error => {
        console.log("error : "+error);
    })

}


/**
 * 아이디와 관련한 메시지는 아이디칸 밑에,
 * 비밀번호와 관련한 메시지는 비밀번호칸 밑에
 * 메시지가 존재한다면 숨겨져있던 메시지 태그를 보여준다.
 * @param loginAnswer ResponseBase 응답객체
 */
function makeHtml(loginAnswer){
    /*
        boolean 타입 필드에 대해 getter는 isXxx() 형식이 권장되며,
        getXxx()가 아니라 isXxx()가 자동으로 인식
        따라서 Jackson 등 직렬화 라이브러리는 getter 이름에서 is 접두사를 빼고 필드명을 normal로 추론
    */


    if (loginAnswer.normal == false) {  //body에 error code가 존재할때- valid에 걸렸을때
        switch (loginAnswer.content.exceptionCode) {
            case "FAIL_LOGIN_VALID":
                console.log("loginAnswer.content.exceptionMessage.errorField : "+loginAnswer.content.exceptionMessage.errorField);
                alertText1 = loginAnswer.content.exceptionMessage.errorField.exceptionMessage;
                break;

            default: //body에 error code가 존재하지 않을때- valid는 통과하였으나 DB 조회결과 일치하지 않을때
                console.log("loginAnswer.content.errorField : "+loginAnswer.content.errorField);
                alertText1 = loginAnswer.content.errorField.exceptionMessage;

        }

        //로그인 경고 메시지 칸 보이기
        loginForm.getItem("checkDiv").show();

    } else { //아이디랑 비번이 맞다.
        window.location.href = "/user/userList/page?pageNumber=1";

        //로그인 경고 메시지 칸 숨기기
        loginForm.getItem("checkDiv").hide();
    }

    loginForm.getItem("checkDiv").setValue(alertText1);
}

