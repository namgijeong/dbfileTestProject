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
                        height: 600,
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
        css: "dhx_widget--bordered fileForm",
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
                            },

                        ]
                    },

                    //div
                    {
                        id: "idFailDiv",
                        width: 480,
                        height: 50,

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
                height:200,
                padding:0,
                css : "loginFormDivDiv",
            },

            //div
            {
                id: "loginFormDivCheck",
                width:490,
                height:200,
                padding:0,
                css : "loginFormDivDiv",
            },
        ]
    });


    //이렇게 해당 레이아웃 cell 에 다시 attach 까지 해주면 화면에 나올거에요
    //attach()는 내부적으로 새롭게 렌더를 트리거하지만 ready 이벤트를 자동으로 다시 내보내지 않음
    layout.getCell("loginForm").attach(loginForm);

    //DHTMLX는 화면이 완전히 그려질 때까지 기다리는 공식적인 Promise API를 제공
    dhx.awaitRedraw().then(() => {

        const idFailText = document.querySelector('[data-cell-id="idFail"] > div');
        if (idFailText) {
            idFailText.textContent = "아이디가 틀렸습니다.";
            idFailText.style.color = "red";
            idFailText.style.fontWeight = "bold";
            idFailText.style.lineHeight = "50px";
            //idFailText.style.display = "none";
        }

        const idDiv = document.querySelector('[data-cell-id="idDiv"] > div');
        if(idDiv) {
            idDiv.textContent = "ID";
            idDiv.style.fontWeight = "bold";
            idDiv.style.fontSize = "20px";
            idDiv.style.color = "black";
            idDiv.style.lineHeight = "100px";
        }
    });

}

const loginFormDivId = {

}