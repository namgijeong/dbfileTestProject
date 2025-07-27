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
			            type: "datepicker",
			            label: language.label_startDate,
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