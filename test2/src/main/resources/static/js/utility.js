
/**
 * Ajax json format 응답일때 자바스크립트 객체 Date로 변환한다.
 * @param dateString 문자열
 * @returns {Date} 객체
 */
function parseDate(dateString) {
    const [datePart, timePart] = dateString.split(" ");
    const [year, month, day] = datePart.split("-");
    const [hour, minute, second] = timePart.split(":");

    const date = new Date(
        parseInt(year),
        parseInt(month) - 1, // JS month is 0-based
        parseInt(day),
        parseInt(hour),
        parseInt(minute),
        parseInt(second)
    );

    console.log(date.getFullYear());
    console.log(date.getMonth() + 1); // (주의: 0-based)
    console.log(date.getDate());
    console.log(date.getHours());
    console.log(date.getMinutes());

    return date;
}


/**
 * 자바스크립트 객체 Date의 각각 연/월/일 부분을 분리 후, 한글을 붙인다.
 * @param date 객체
 * @returns string 문자열
 */
function makeRegDate(date) {
    let regDateText = '';
    regDateText += date.getFullYear()+"년";
    regDateText += (parseInt(date.getMonth())+1)+"월";
    regDateText += (date.getDate())+"일    ";

    if (parseInt(date.getHours()) < 10) {
        regDateText += "0"+(date.getHours())+"시";
    } else {
        regDateText += (date.getHours())+"시";
    }

    if (parseInt(date.getMinutes()) < 10) {
        regDateText += "0"+(date.getMinutes())+"분";
    } else {
        regDateText += (date.getMinutes())+"분";
    }

    return regDateText;

}


/**
 *  Ajax json이 아닌 자바 객체 응답일때 LocalDateTime의 각각 연/월/일 부분을 분리 후, 한글을 붙인다.
 * @param localDateTime 2021-01-03T10:00 형식의 LocalDateTime
 * @returns string 문자열
 */
function makeLocalDateTimeToString(localDateTime) {
    const date = new Date(localDateTime);
    let regDateText = makeRegDate(date);
    return regDateText;
}


/**
 * picker.getDate에서 나온 결과를 바꾸기 위해
 * @param date date객체
 * @returns {string} yyyy-mm-dd hh:mm:ss 형태 문자열
 */
function makeEasePickDateToString(date) {
    //  자바스크립트에서 Date를 숫자로 표현하려면 항상 timestamp 형태가 된다. => number (timestamp) '2025-07-21' 형태로 전달

    // ISO 형식은 JS 엔진이 공식적으로 파싱할 수 있는 포맷
    // toISOString()은 number로 변환하는 게 아니다. string 형태로 반환한다.
    // T 아래로 시간을 자른다.
    //하지만 이걸쓰면 toISOString UTC 때문에 한국시간과 하루 차이가 난다
    // let easePickDateString = easePickDate.toISOString().split('T')[0]
    // return easePickDateString;

    let regDateText = '';
    regDateText += date.getFullYear()+"-";

    if (parseInt(date.getMonth())+1 < 10) {
        regDateText += "0"+(parseInt(date.getMonth())+1)+"-";
    } else {
        regDateText += (parseInt(date.getMonth())+1)+"-";
    }

    if (parseInt(date.getDate()) < 10) {
        regDateText += "0"+(date.getDate())+" ";
    } else {
        regDateText += (date.getDate())+" ";
    }

    if (parseInt(date.getHours()) < 10) {
        regDateText += "0"+(date.getHours())+":";
    } else {
        regDateText += (date.getHours())+":";
    }

    if (parseInt(date.getMinutes()) < 10) {
        regDateText += "0"+(date.getMinutes())+":";
    } else {
        regDateText += (date.getMinutes())+":";
    }

    if (parseInt(date.getSeconds()) < 10) {
        regDateText += "0"+(date.getSeconds());
    } else {
        regDateText += (date.getSeconds());
    }

    console.log("regDateText :"+regDateText);
    return regDateText;
}

/**
 * 필드가 localdatetime인 dto에 넣기 위해서 어쩔수 없이 형태를 바꾸어야한다.
 * 시간을 무시하기 위해 강제로 임의의 00을 넣어준다.
 * @param string 문자열
 * @returns {string} 문자열
 */
function makeEasePickDateToDTOString(string) {
    let dtoString = string+ " 00:00:00";
    return dtoString;
}


/**
 * 필드를 화면에 출력할때 null이면 아예 공백으로 출력하기 위해
 * @param originText  string 문자열
 * @returns cleanText string 문자열
 */
function makeNullToBlank(originText) {
    let cleanText = "";
    if (originText === undefined || originText == null) {
        cleanText = "";
    } else {
        cleanText = originText;
    }
    return cleanText;
}

/**
 * 검색할때 필드가 공백이거나 아무것도 입력안했으면 null로 만들어 자바객체와 매핑하기 위해
 * @param originText string 문자열
 * @returns {*|string} string 문자열 혹은 null
 */
function makeBlankToNull(originText) {
    let cleanText = originText.trim();
    if (cleanText === "" || cleanText ==='') {
        cleanText = null;
    }
    return cleanText;
}