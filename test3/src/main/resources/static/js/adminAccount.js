var layout;
var form;
var grid;
var pagination;
var createAdminForm;
var editForm
var createLinkList, editLinkList;
var permitLevelList = [];

const init = () => {

	createLayout();

	settingForm();

	settingGrid();

	searchAdminList();

	loadPermitLevel();
	//관리자 계정 등록 폼
	createForm();

	//관리자 계정 수정 폼
	createEditForm();

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
						id: "form",
						height: 55,
						padding: 10
					},
					{
						id: "grid",
						padding: 10,
					},
				]
	        },
			// 신규 관리자 계정 생성 및 기존 관리자 계정 수정(우)
			{
				id: "edit-cell",
				gravity: 0.4,
				hidden: true,
				rows: [
					{
						id: "edit-toolbar",
						height: "content",
						padding: 10
					},
					{
						id: "edit-form",
						height: "content"
					}
				]
			},
			{
				id: "create-cell",
				gravity: 0.4,
				hidden: true,
				rows: [
					{
						id: "createForm",
						height: "content",
					},
					{
						id: "createButton",
						height: "content",
					}
				]
			}
	    ]
	});
}

//레이아웃 만들어놓고
// 레이아웃 내부에 id 가 form 인 부분을 선언했다면
const settingForm = () => {

	form = new dhx.Form(null, {
	    css: "dhx_widget--bordered",
	    padding: 0,
	    rows: [
		    {
				cols: [
					{
						type: "spacer"
					},
				    {
			            name: "addAdmin",
			            id: "addAdmin",
			            type: "button",
			            value: language.button_add,
			            submit: false,
			            css: "marginLeft10"
			        }
			    ]
			}
		]
	});

	form.events.on("click", (id, event) => {
		switch (id) {
			case "addAdmin":
				createAdminForm.clear("value");

				layout.getCell("create-cell").show();
				layout.getCell("edit-cell").hide();
				break;
		}
	});
	//이렇게 해당 레이아웃 cell 에 다시 attach 까지 해주면 화면에 나올거에요
	layout.getCell("form").attach(form);
}

const settingGrid = () => {
	const config = {
	    columns: [
	        { id: "loginId", header: [{ text: language.gridHeader_loginId }] },
	        { id: "adminName", header: [{ text: language.gridHeader_adminName }] },
	        { id: "levelName", header: [{ text: language.gridHeader_levelName }] },
	        { hidden: true, id: "adminId", header: [{ text: '' }] },
	        { hidden: true, id: "tenantId", header: [{ text: '' }] },
	        { hidden: true, id: "loginPasscode", header: [{ text: '' }] },
	        { hidden: true, id: "permitLevel", header: [{ text: '' }] },
	    ],
	    autoWidth: true,
	    sortable: false,
	    tooltip: false
	};

	if(grid) {
		grid.destructor();
	}

	grid = new dhx.Grid(null, config);
	grid.selection.setCell(grid.data.getItem(grid.data.getId(0)), grid.config.columns[0]);

	grid.events.on("cellClick", (event, value) => {
		let adminId = event.adminId;
		let tenantId = event.tenantId;

		if (adminId) {
			openEditor(event);
		}
	});

	layout.getCell("grid").attach(grid);
}

const createForm = () => {
	const createAdminFormConfig = {
		rows: [
			{
				id: "closeCreateForm",
				type: "button",
				icon: "dxi dxi-close",
				view: "link",
				color: "danger",
				circle: true,
				css: "createFormClose",
			},
			{
				id: "adminId",
				type: "input",
				name: "adminId",
				hidden: true
			},
			{
				id: "tenantId",
				type: "input",
				name: "tenantId",
				hidden: true
			},
			{
				type: "input",
				name: "loginId",
				labelPosition: "top",
				label: language.editForm_loginId,
				placeholder: language.editForm_loginId,
				helpMessage: language.helpMessage_createId,
				padding: 10,
			},
			{
				id: "duplicateCheck",
				name: "duplicateCheck",
				type: "input",
				hidden: true
			},
			{
				id: "loginIdNode",
				name: "loginIdNode",
				type: "input",
				hidden: true
			},
			{
				type: "button",
				name: "duplicate",
				value: language.button_duplicateCheck,
				css: "duplicateButton",
			},
			{
				type: "input",
				inputType: "password",
				id: "loginPasscode",
				name: "loginPasscode",
				labelPosition: "top",
				label: language.label_password,
				placeholder: language.placeholder_createPassword,
				padding: 10,
				autocomplete: false
			},
			{
				id: "loginPasscodeCheck",
				name: "loginPasscodeCheck",
				type: "input",
				inputType: "password",
				label: language.editForm_passwordCheck,
				labelPosition: "top",
				placeholder: language.editForm_passwordCheck,
				preMessage: language.helpMessage_password,
				padding: 10,
				autocomplete: false
			},
			{
				type: "input",
				name: "adminName",
				labelPosition: "top",
				label: language.gridHeader_adminName,
				padding: 10
			},
			{
				type: "select",
				id: "permitLevel",
				name: "permitLevel",
				labelPosition: "top",
				label: language.gridHeader_levelName,
				padding: 10,
				helpMessage: language.helpMessage_levelName,
				options: permitLevelList
			},

			{
				cols: [
					{
						type: "spacer"
					},
					{
						type: "button",
						name: "createAdminButton",
						id: "createAdminButton",
						value: language.button_add,
						padding: 10
					}
				]
			}
		]
	};

	createAdminForm = new dhx.Form(null, createAdminFormConfig);

	createAdminForm.events.on("click", (id) => {
		switch (id) {
			case "closeCreateForm":
				createAdminForm.clear();
				layout.getCell("create-cell").hide();
				break;
			case "duplicate":
				let idRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,16}$/;

				if (createAdminForm.getItem("loginId").getValue() == "") {
					errorMessage(language.failMessage_noId);
					return false;
				} else if (TENANT_CONFIG.COUNSELOR_LOGINID_REGEX_USE_FLAG == "Y") {
					if (!idRegex.test(createAdminForm.getItem("loginId").getValue())) {
						errorMessage(language.helpMessage_createId);
						return false;
					} else {
						checkDuplicateId("create");
					}
				} else {
					checkDuplicateId("create");
				}

				break;
			case "createAdminButton":
				layout.getCell("createForm").progressShow();

				let loginIdNode = createAdminForm.getItem("loginIdNode").getValue();
				let loginId = createAdminForm.getItem("loginId").getValue();
				let duplicateCheck = createAdminForm.getItem("duplicateCheck").getValue();

				if (duplicateCheck != "Y" || loginIdNode != loginId) {
					// 중복체크 하고 바꾼 다음 다시 체크 안 한 경우
					errorMessage(language.failMessage_noDuplicate);
					layout.getCell("createForm").progressHide();
					return false;
				} else {
					// 중복체크 잘 수행한 경우
					if (!createAdminValidation()) {
						layout.getCell("createForm").progressHide();
						return false;
					} else {
						createAdmin();
					}
				}
				break;
		}
	});

	layout.getCell("createForm").attach(createAdminForm);
}

// 관리자 권한 리스트 가져오기
const loadPermitLevel = () => {
	$.ajax({
		url: "adminAccount/permitLevel?tenantId=" + TENANTID,
		method: "get",
		dataType: "json",
		async: false,
		error: (e) => {
			let status = e.status;
			console.log('관리자 권한 목록 조회 오류 : ' + status);
			errorMessage(language.errorMessage_levelName);
		},
		success: (data) => {
			let nonePermitLevel = {
				"content": language.selectOption_seletLevelName
				, "value": null
			}

			permitLevelList.push(nonePermitLevel);

			switch (data.status) {
				case 0:
					errorMessage(language.failMessage_levelName);
					break;
				case 1:
					let result = data.payload.result;

					if (result.length == 0) {
						return false;
					}

					for (var i = 0; i < result.length; i++) {
						let content = result[i].levelName;

						let permitLevel = {
							"content": content
							, "value": result[i].permitLevel
						};

						permitLevelList.push(permitLevel);
					}
					break;
			}
		}
	});
}


const createEditForm = (data) => {
	//1) 상단 툴바만들기
	createEditToolbar();

	const editFormConfig = {
		rows: [
			{
				id: "adminId",
				type: "input",
				name: "adminId",
				hidden: true
			},
			{
				id: "tenantId",
				type: "input",
				name: "tenantId",
				hidden: true
			},
			{
				id: "tenantName",
				type: "input",
				name: "tenantName",
				hidden: true
			},
			{
				id: "loginIdNode",
				type: "input",
				name: "loginIdNode",
				hidden: true
			},
			{
				id: "originalLoginId",
				type: "input",
				name: "originalLoginId",
				hidden: true
			},
			{
				id: "duplicateCheck",
				name: "duplicateCheck",
				type: "input",
				hidden: true
			},
			{
				type: "input",
				name: "adminName",
				label: language.editForm_adminName,
			},
			{
				type: "input",
				name: "loginId",
				label: language.editForm_loginId,
			},
			{
				type: "button",
				name: "duplicate",
				value: language.button_duplicateCheck,
				css: "editDuplicateButton",
			},
			{
				type: "input",
				inputType: "password",
				id: "loginPasscode",
				name: "loginPasscode",
				label: language.editForm_password,
				placeholder: language.editForm_createPassword,
				autocomplete: false
			},
			{
				id: "loginPasscodeCheck",
				name: "loginPasscodeCheck",
				type: "input",
				inputType: "password",
				label: language.editForm_passwordCheck,
				labelPosition: "top",
				placeholder: language.editForm_passwordCheck,
				preMessage: language.helpMessage_password,
				autocomplete: false
			},

			{
				type: "select",
				id: "permitLevel",
				name: "permitLevel",
				labelPosition: "top",
				label: language.gridHeader_levelName,
				helpMessage: language.helpMessage_levelName,
				options: permitLevelList
			},

		]
	};

	editForm = new dhx.Form(null, editFormConfig);

	editForm.getItem("duplicate").events.on("click", (event, value) => {
		layout.getCell("edit-form").progressShow();
		let idRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,16}$/;

		if (editForm.getItem("loginId").getValue() == "") {
			errorMessage(language.failMessage_noId);
			layout.getCell("edit-form").progressHide();
			return false;
		} else if (TENANT_CONFIG.COUNSELOR_LOGINID_REGEX_USE_FLAG == "Y") {
			if (!idRegex.test(editForm.getItem("loginId").getValue())) {
				errorMessage(language.helpMessage_createId);
				layout.getCell("edit-form").progressHide();
				return false;
			} else {
				checkDuplicateId("edit");
			}
		} else {
			checkDuplicateId("edit");
		}

		layout.getCell("edit-form").progressHide();
	});

	layout.getCell("edit-form").attach(editForm);
}



const openEditor = (item) => {
	createAdminForm.clear();

	layout.getCell("create-cell").hide();
	layout.getCell("edit-cell").show();

	if(!item) {
		return;
	}

	editForm.clear("value");
	editForm.setValue(item);
	editForm.getItem("loginIdNode").setValue(item.loginId);
}

const searchAdminList = () => {
	$.ajax({
		url: "adminAccount/adminAccount",
		contentType: 'application/json',
		data: {'tenantId' : TENANTID},
		method: "GET",
		async: true,
		error: (e) => {
			let status = e.status;
			console.log('관리자 리스트 조회 오류 : ' + status);
			errorMessage(language.errorMessage_findAdmin);
		},
		success: (data) => {
			switch(data.status){
				case 0:
					errorMessage(language.failMessage_findAdmin);
					//그리드 로드전에 초기화
					beforeDataGrid(grid);

					break;
				case 1:
					if(data.payload.result.total_count === 0){
						//그리드 로드전에 초기화
						beforeDataGrid(grid);

						dhx.awaitRedraw().then(() => {
							$(".dhx_grid-body").addClass("noneData");
						});

						noneDataGrid(grid);
					}else{
						grid.data.parse(data.payload.result);

						$(".dhx_grid-body").removeClass("noneData");
					}
					break;
			}
		}
	});
}

// 관리자 추가 유효성 검사
const createAdminValidation = () => {
	let adminData = createAdminForm.getValue();

	if (adminData.loginPasscode != "" && adminData.loginPasscode != null) {
		let passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[`~!@#$%^&*()\-_=+[{\]}\\|;:'",<.>/?]).{8,16}$/;

		if (!passwordRegex.test(adminData.loginPasscode)) {
			errorMessage(language.helpMessage_password);
			return false;
		}
	}

	if (adminData.loginPasscode != adminData.loginPasscodeCheck) {
		errorMessage(language.errorMessage_noMatchPasscode);
		return false;
	}

	if (adminData.adminName == "" || adminData.adminName == null) {
		errorMessage(language.errorMessage_inputAdminName);
		return false;
	}

	if (adminData.permitLevel == "" || adminData.permitLevel == null || permitLevel == undefined) {
		errorMessage(language.errorMessage_inputPermitLevel);
		return false;
	}

	return true;
}

// 관리자 추가 함수
const createAdmin = () => {
	let referenceList = [];

	createAdminForm.getItem("tenantId").setValue(TENANTID);
	createAdminForm.getItem("adminId").setValue("0");

	$.ajax({
		url: "adminAccount/adminAccount",
		contentType: "application/json",
		data: JSON.stringify(createAdminForm.getValue()),
		method: "POST",
		async: false,
		error: (e) => {
			let status = e.status;
			console.log("직원 추가 오류 : " + status);
			errorMessage(language.errorMessage_addAdmin);

			dhx.awaitRedraw().then(() => {
				layout.getCell("create-cell").hide();
			});
		},
		success: (data) => {
			switch (data.status) {
				case 0:
					errorMessage(language.failMessage_addAdmin);
					dhx.awaitRedraw().then(() => {
						layout.getCell("create-cell").hide();
					});
					break;
				case 1:
					// createAdminForm.getItem("permitLevel").setOptions(loadPermitLevel());
					message(language.message_addAdmin);

					layout.getCell("createForm").progressHide();

					dhx.awaitRedraw().then(() => {
						layout.getCell("create-cell").hide();
					});

					searchAdminList();

					break;
				case -2:
					errorMessage(language.errorMessage_cantUsePassword);
					layout.getCell("createForm").progressHide();
					break;
				case -3:
					errorMessage(language.failMessage_duplicate);
					editForm.getItem("duplicateCheck").setValue("N");
					layout.getCell("createForm").progressHide();
					break;
			}
		}
	});

}

// 관리자 아이디 중복확인
const checkDuplicateId = (mode) => {

	let parameter = mode == "edit" ? editForm : createAdminForm;

	$.ajax({
		url: "adminAccount/checkDuplicateId?tenantId=" + TENANTID,
		method: "get",
		contentType: "application/json",
		data: parameter.getValue(),
		async: false,
		error: (e) => {
			console.log("중복 확인 오류 : " + e.status);
			errorMessage(language.errorMessage_duplicateId);
		},
		success: (data) => {
			switch (data.status) {
				case 0:
					errorMessage(language.failMessage_duplicateId)
					break;
				case 1:
					let result = data.payload.result
					if (result.length > 0) {
						parameter.getItem("duplicateCheck").setValue("N");
						parameter.getItem("loginIdNode").setValue("");
						errorMessage(language.failMessage_duplicate);
					} else {
						parameter.getItem("duplicateCheck").setValue("Y");

						let loginIdNode = parameter.getItem("loginId").getValue();
						parameter.getItem("loginIdNode").setValue(loginIdNode);

						succMessage(language.successMessage_duplicateId);
					}
			}
		}
	});
}

// 관리자 수정 폼 상단 버튼 폼 그리기
const createEditToolbar = () => {

	const editToolbarForm = new dhx.Form(null, {
		css: "furence-custom-form",
		padding: 0,
		width: "100%",
		cols: [
			{
				id: "close",
				type: "button",
				icon: "dxi dxi-close",
				view: "link",
				color: "danger",
				circle: true
			},
			{
				type: "spacer"
			},
			{
				id: "editApply",
				type: "button",
				value: language.button_apply
			},
			{
				id: "editDelete",
				type: "button",
				color: "danger",
				value: language.button_delete
			}
		]
	});

	editToolbarForm.events.on("click", (id) => {
		switch (id) {
			case "close":
				closeEditor();
				break;
			case "editApply":
				showConfirm(language.confirmTitle_updateAdmin, language.confirm_updateAdmin, () => {
					let loginIdNode = editForm.getItem("loginIdNode").getValue();
					let loginId = editForm.getItem("loginId").getValue();
					let duplicateCheck = editForm.getItem("duplicateCheck").getValue();

					if (duplicateCheck != "Y" || loginIdNode != loginId) {
						// 중복체크 하고 바꾼 다음 다시 체크 안 한 경우
						errorMessage(language.failMessage_noDuplicate);
						return false;
					} else {
						// 중복체크 잘 수행한 경우
						if (!updateAdminValidation()) {
							return false;
						}
					}
					updateAdmin();
				});

				break;
			case "editDelete":
				showConfirm(language.confirmTitle_deleteAdmin, language.confirm_deleteAdmin, () => {
					deleteAdmin();
				});

				break;
		}
	});

	layout.getCell("edit-toolbar").attach(editToolbarForm);
}

// 관리자 수정 폼 닫기
const closeEditor = () => {
	editForm.clear();

	layout.getCell("edit-cell").hide();
}

// 관리자 수정 유효성 검사
const updateAdminValidation = () => {
	let data = editForm.getValue();

	if (data.adminName == null || data.adminName == "") {
		errorMessage(language.errorMessage_inputAdminName);
		return false;
	}

	if (data.permitLevel == "" || data.permitLevel == null || permitLevel == undefined) {
		errorMessage(language.errorMessage_inputPermitLevel);
		return false;
	}

	if (data.loginPasscode != "" && data.loginPasscode != null) {
		let passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[`~!@#$%^&*()\-_=+[{\]}\\|;:'",<.>/?]).{8,16}$/;

		if (!passwordRegex.test(data.loginPasscode)) {
			errorMessage(language.helpMessage_password);
			return false;
		}

		if (data.loginPasscode != data.loginPasscodeCheck) {
			errorMessage(language.errorMessage_noMatchPasscode);
			return false;
		}
	}

	return true;
}

// 관리자 수정
const updateAdmin = () => {

	editForm.getItem("tenantId").setValue(TENANTID);
	editForm.getItem("tenantName").setValue(TENANTNAME);
	editForm.getItem("originalLoginId").setValue(editForm.getItem("loginIdNode").getValue());

	$.ajax({
		url: "adminAccount/adminAccount",
		contentType: "application/json",
		data: JSON.stringify(editForm.getValue()),
		method: "PUT",
		async: false,
		error: (e) => {
			let status = e.status;
			console.log("직원 수정 오류 : " + status);
			errorMessage(language.errorMessage_updateAdmin);

			dhx.awaitRedraw().then(() => {
				layout.getCell("create-cell").hide();
			});
		},
		success: (data) => {
			switch (data.status) {
				case 0:
					errorMessage(language.failMessage_updateAdmin);

					dhx.awaitRedraw().then(() => {
						layout.getCell("create-cell").hide();
					});
					break;
				case 1:
					message(language.message_updateAdmin);

					closeEditor();

					searchAdminList();

					break;
				case -2:
					errorMessage(language.errorMessage_cantUsePassword);

					dhx.awaitRedraw().then(() => {
						layout.getCell("create-cell").hide();
					});
					break;
			}
		}
	});
}

// 관리자 게정 삭제
const deleteAdmin = () => {
	editForm.getItem("tenantName").setValue(TENANTNAME);
	editForm.getItem("originalLoginId").setValue(editForm.getItem("loginIdNode").getValue());

	$.ajax({
		url: "adminAccount/deleteAdminAccount",
		method: "PUT",
		contentType: "application/json",
		data: JSON.stringify(editForm.getValue()),
		async: false,
		error: (e) => {
			let status = e.status;
			console.log("직원 삭제 오류 : " + status);
			errorMessage(language.errorMessage_deleteAdmin);
		},
		success: (data) => {
			switch (data.status) {
				case 0:
					errorMessage(language.failMessage_deleteAdmin);
					break;
				case 1:
					message(language.message_deleteAdmin);

					closeEditor();

					searchAdminList();

					break;
			}
		}
	});
}
