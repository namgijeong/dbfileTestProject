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
                    {
                        id: "idFailDiv",
                        width: 480,
                        height: 50,
                        css : "loginFormDivDiv",

                        cols:[
                            {
                                id: "idFail",
                                width:400,
                                height: 50,
                            },

                        ]

                    }

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
                    {
                        id: "pwdFailDiv",
                        width: 480,
                        height: 50,
                        css : "loginFormDivDiv",

                        cols:[
                            {
                                id: "pwdFail",
                                width:400,
                                height: 50,
                            },

                        ]

                    }

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
    responseDivForm.events.on("click", (id, event) => {
        switch (id) {
            //이걸 클릭했을때
            case "findAllButton":
                console.log("파일 올리기 버튼 클릭");
                //여기다가 클릭후 ajax 실행시켜서 동적으로 grid 생성해야함
                ajaxSelectAll(event);
                break;
        }
    });

    //이렇게 해당 레이아웃 cell 에 다시 attach 까지 해주면 화면에 나올거에요
    //attach()는 내부적으로 새롭게 렌더를 트리거하지만 ready 이벤트를 자동으로 다시 내보내지 않음
    layout.getCell("loginForm").attach(loginForm);

    //DHTMLX는 화면이 완전히 그려질 때까지 기다리는 공식적인 Promise API를 제공
    dhx.awaitRedraw().then(() => {

        const idFailText = document.querySelector('[data-cell-id="idFail"] > div');
        if (idFailText) {
            idFailText.textContent = "아이디가 틀렸습니다.";

            idFailText.classList.add("checkDiv");
            idFailText.classList.add("failHidden");
        }


        const idDivText = document.querySelector('[data-cell-id="idDiv"] > div');
        if(idDivText) {
            idDivText.textContent = "ID";

            idDivText.classList.add("itemDiv");
        }

        const pwdFailText = document.querySelector('[data-cell-id="pwdFail"] > div');
        if (pwdFailText) {
            pwdFailText.textContent = "비밀번호가 틀렸습니다.";

            pwdFailText.classList.add("checkDiv");
            pwdFailText.classList.add("failHidden");
        }

        const pwdDivText = document.querySelector('[data-cell-id="pwdDiv"] > div');
        if(pwdDivText) {
            pwdDivText.textContent = "PWD";

            pwdDivText.classList.add("itemDiv");
        }

        const checkDivText = document.querySelector('[data-cell-id="checkDiv"] > div');
        if(checkDivText) {
            checkDivText.textContent = "로그인을 다시 시도해주세요.";

            checkDivText.classList.add("checkDiv");
            checkDivText.classList.add("failHidden");
        }
    });

}

