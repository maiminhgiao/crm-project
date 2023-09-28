/**
 * 
 */


$(document).ready(function() {
	/*Add User*/
	$("#form-add").on("submit", function(event) {
		event.preventDefault()
		var dataAdd = {
			fullName: $("#fullName").val(),
			email: $("#email").val(),
			pwd: $("#pwd").val(),
			phone: $("#phone").val(),
			idRole: $("#idRole").val()
		}
		$.ajax({
			method: "POST",
			url: "http://localhost:8080/CrmProject/api/user/add",
			data: dataAdd,
			success: function() {
				alert("Add Sucess " + $("#fullName").val())
				location.reload()
			}
		})
			.done(function(res) {

			});
	});
	/*Delete User*/
	$(".delete-btn").on("click", function() {
		var This = $(this)
		var dataDelete = {
			id: $(this).attr('idDelete')
		}
		$.ajax({
			method: "POST",
			url: "http://localhost:8080/CrmProject/api/user/delete",
			data: dataDelete
		})
			.done(function(res) {
				This.closest('tr').remove()
			});
	});
	$("#content-modal").hide()
	/*Update User*/
	$(".update-btn").on("click", function() {
		$(".overlay").on("click", function() {
			$("#content-modal").hide()
		});
		$(".cancel-btn").on("click", function() {
			$("#content-modal").hide()
		});
		$("#content-modal").show()

		var This = $(this)
		$.ajax({
			method: "POST",
			url: "http://localhost:8080/CrmProject/api/user/user",
			data: { id: $(this).attr('idUpdate') }
		})
			.done(function(res) {
				console.log(res.data.fullName);
				$("#fullName").val(res.data.fullName),
					$("#email").val(res.data.email),
					$("#pwd").val(res.data.pwd),
					$("#phone").val(res.data.phone),
					$("#idRole").val(res.data.idRole)

				/*Submit form*/

				$("#form-update").on("submit", function(e) {
					e.preventDefault()
					var dataUpdate = {
						id: This.attr('idUpdate'),
						fullName: $("#fullName").val(),
						email: $("#email").val(),
						pwd: $("#pwd").val(),
						phone: $("#phone").val(),
						idRole: $("#idRole").val()
					}
					$.ajax({
						method: "POST",
						url: "http://localhost:8080/CrmProject/api/user/update",
						data: dataUpdate,
						success: function() {
							alert("Update Sucess")
							location.reload()
						}
					})
						.done(function(msg) {

						});
				});

			});

	});


	path = "http://localhost:8080/CrmProject/user-detail"
	$('.view-btn').on("click", function() {
		var This = $(this)
		$.ajax({
			method: "POST",
			url: "http://localhost:8080/CrmProject/api/user/user",
			data: { id: $(this).attr('idView') }
		})
			.done(function(res) {
				var dataView = res
				console.log(dataView)
				localStorage.setItem("dataView", dataView)
				
				window.location.path
			})
	})
	$(document).ready(function() {
		var dataView = localStorage.getItem("dataView")
		console.log(dataView)
		/*if(Object.keys(dataView).length !== 0){
		$("#fullNameView").val(dataView.data.fullName),
			$("#emailView").val("adad-a0da-")}*/
	})
})