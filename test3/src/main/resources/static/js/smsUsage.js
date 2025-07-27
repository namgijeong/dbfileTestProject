var layout;
var form;
var grid;
var pagination;
var searchData;

const initSmsUsageManagement = () => {
	createLayout();
	settingForm();
	settingGrid();
	
	findSmsUsage(0);
	
	top.layout.getCell("contents").progressHide();
}

const createLayout = () => {
	layout = new dhx.Layout("layout", {
	    type: "line",
	    css: "contentsTitleWrap",
	    header: top.menuTitle,
	    cols: [
	        {
	            id: "main-content",
				header: BUSINESS_NAME,
	            rows: [
					{
						id: "searchForm",
						height: 80
					},
					{
						id: "grid",
					},
					{
						id:	"pagination",
						height: 45
					}
				]
	        },
	    ]
	});
}

// 요금 임시 미표출
const settingGrid = () => {
	const config = {
	    columns: [
	        { id: "smsDate", header: [{ text: language.gridHeader_date, align:"center" }] },
	        { id: "smsCount", header: [{ text: language.gridHeader_smsCount, align:"center" }], align: "right", format: "#,###" },
//	        { id: "smsCost", header: [{ text: language.gridHeader_smsCost, align:"center" }], align: "right", format: "#,###" },
	        { id: "lmsCount", header: [{ text: language.gridHeader_lmsCount, align:"center" }], align: "right", format: "#,###" },
//	        { id: "lmsCost", header: [{ text: language.gridHeader_lmsCost, align:"center" }], align: "right", format: "#,###" },
	        { id: "mmsCount", header: [{ text: language.gridHeader_mmsCount, align:"center" }], align: "right", format: "#,###" },
//	        { id: "mmsCost", header: [{ text: language.gridHeader_mmsCost, align:"center" }], align: "right", format: "#,###" },
//	        { id: "totalCost", header: [{ text: language.gridHeader_total, align:"center" }], align: "right", format: "#,###" },
	    ],
	    autoWidth: true,
	    sortable: false,
	    tooltip: true
	};
	
	if(grid) {
		grid.destructor();
	}
	
	grid = new dhx.Grid(null, config);
	grid.selection.setCell(grid.data.getItem(grid.data.getId(0)), grid.config.columns[0]);
	layout.getCell("grid").attach(grid);
}

const settingForm = () => {
	
	form = new dhx.Form(null, {
	    css: "dhx_widget--bordered",
	    padding: "20px 20px 10px 30px",
	    rows: [
		    {
				cols: [
					{
			            name: "smsUsageStartDate",

						//DatePicker는 날짜를 선택할 수 있는 달력 UI 위젯
			            type: "datepicker",

			            label: language.label_startDate,
			            width: 220,

						//defines the position of a label: "left"|"top"
			            labelPosition: "left",

						//타임피커에 닫기 및 저장 버튼이 있는지 여부를 정의
						//controls: true (기본값) [확인/취소/지우기 버튼 표시됨]
						//controls: false [버튼 없이 달력만 표시됨]
			            controls: false,

						//(선택 사항) 시간 선택기의 시간 형식을 정의합니다: 12시간 또는 24시간(기본값은 12 또는 24)
			            timeFormat: 24,

						//dhx.Form 안에 포함된 각 form item(= control 요소)들의 레이아웃 옵션
						// "cols"	라벨과 입력 필드를 가로로 나란히 배치 (기본값)
						// "rows"	라벨을 위에, 입력 필드를 세로로 쌓아서 배치
			        	dir: "cols",

			        	css: "smsUsageDate",

						//(선택 사항) 달력의 날짜 형식을 정의합니다. 기본값은 "%d/%m/%y"입니다. 날짜 형식에는 구분 기호(공백 또는 기호)가 포함되어야 합니다. 그렇지 않으면 오류가 발생합니다.
			        	dateFormat: "%Y-%m-%d",

						//(선택 사항) 날짜 선택기의 값
						//value는 datepicker의 기본 값(선택된 날짜) 을 지정하는 속성
			        	value: getStringToDate(new Date())
			        },
			        {
			            name: "smsUsageEndDate",
			            type: "datepicker",
			            label: language.label_endDate,
			            width: 220,
			            labelPosition: "left",
			            controls: false,
			            timeFormat: 24,
			        	dir: "cols",
			        	css: "smsUsageDate",
			        	dateFormat: "%Y-%m-%d",
			        	value: getStringToDate(new Date())
			        },
			        {
			            name: "limit",
			            type: "input",
			            hidden: true,
			            hiddenLabel: true,
			        },
			        {
			            name: "from",
			            type: "input",
			            hidden: true,
			            value: 15,
			            hiddenLabel: true,
			        },
			        {
			            name: "tenantId",
			            type: "input",
			            hidden: true,
			            hiddenLabel: true,
			        },
			        {
			            name: "language",
			            type: "input",
			            hidden: true,
			            hiddenLabel: true,
			        },
			        {
						id: "findSmsUsage",
			            name: "findSmsUsage",
			            type: "button",
			            value: language.button_search,
			            submit: false
			        },
			        {
						id: "excelDown",
			            name: "excelDown",
			            type: "button",
			            value: language.button_excelDownload,
			            submit: false
			        }
			    ]
			}
		]
	});

	//getItem => 폼 컨트롤 객체에 대한 액세스를 제공
	//폼 컨트롤의 이름 또는 해당 ID(컨트롤의 구성에 이름 속성이 정의되지 않은 경우)

	//form.getItem("smsUsageStartDate").getValue()
	//해당 id 또는 name을 가진 단일 필드 컴포넌트를 가져온 후
	//그 필드의 getValue() 메서드를 호출

	// form.getValue()
	//폼 전체의 값을 객체 형태로 반환
	// 각 name 속성을 키로 갖는 JSON 형식
	form.getItem("findSmsUsage").events.on("click", (event) => {
		let startDate = form.getItem("smsUsageStartDate").getValue();
		let endDate = form.getItem("smsUsageEndDate").getValue();
		
		if(!stringDateValidation(startDate, endDate)) { return; }
		
		findSmsUsage(0);
	});
	form.getItem("excelDown").events.on("click", (event) => {
		form.getItem("tenantId").setValue(TENANTID);
		form.getItem("language").setValue(LANGUAGE_CODE);
		excelDown(searchData, grid, "smsUsage");
	});
	
	layout.getCell("searchForm").attach(form);
}

const findSmsUsage = (from) =>{
	layout.progressShow();

	form.setValue({
	    "from": (GRIDLIMIT*from), 
	    "limit": GRIDLIMIT,
	    "tenantId": TENANTID,
	    "language": LANGUAGE_CODE
	});

	if(pagination != null){
		pagination.destructor();
	}
	
	searchData = form.getValue();
	
	$.ajax({
		url: "findSmsUsage",
		contentType: 'application/json',
		data: form.getValue(),
		method: "GET",
		async: true,
		error: (e) => {
			let status = e.status;
			console.log('문자 사용량 조회 오류 : ' + status);
			errorMessage(language.errorMessage_findSmsUsage);
		},
		success: (data) => {
			
			switch(data.status){
				case 0:
					errorMessage(language.failMessage_findSmsUsage);
					//그리드 로드전에 초기화
					beforeDataGrid(grid);
					
					break;
				case 1:
					if(data.payload.result.total_count == 0){
						//그리드 로드전에 초기화
						beforeDataGrid(grid);
						
						dhx.awaitRedraw().then(() => {
							$(".dhx_grid-body").addClass("noneData");							
						});
						
						noneDataGrid(grid);
					}else{
						dhx.awaitRedraw().then(() => {
							grid.data.parse(data.payload.result);
							$(".dhx_grid-body").removeClass("noneData");
						});
					}
					pagination = new dhx.Pagination(null, {
					    css: "pagination dhx_widget--bordered dhx_widget--no-border_top",
					    data: grid.data,
					    pageSize: GRIDLIMIT
					});
		
					layout.getCell("pagination").attach(pagination);
					pagination.setPage(from);
		
					pagination.events.on("change", (index, previousIndex) => {
						findSmsUsage(index);
					});
					break;
			}
		}
	});
	
	layout.progressHide();
}