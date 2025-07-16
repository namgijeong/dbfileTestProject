
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
    regDateText += (date.getMonth()+1)+"월";
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