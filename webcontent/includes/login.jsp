<div class="modal fade" id="loginModal" tabindex="-1" role="">
	<div class="modal-dialog modal-login" role="document">
		<div class="modal-content" id="modalShake">
			<div class="card card-signup card-plain">
				<div class="modal-header">
					<div class="card-header card-header-primary preto">
						<div class="nav-tabs-navigation">
							<div class="nav-tabs-wrapper">
								<ul class="nav nav-tabs" data-tabs="tabs">
									<li class="nav-item"><a class="nav-link active show"
										href="#entrar" data-toggle="tab"> <i
											class="material-icons">people</i> Entrar
											<div class="ripple-container"></div></a></li>
									<li class="nav-item"><a class="nav-link" href="#registrar"
										data-toggle="tab"> <i class="material-icons">assignment</i>
											Registrar
											<div class="ripple-container"></div></a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="card-body ">
					<div class="tab-content text-center">
						<div class="tab-pane active show" id="entrar">
							<div class="modal-body">
								<form class="form" method="post" autocomplete="off" id="form-validate" onsubmit="return false">
									<div class="card-body">
										<p id="msg"></p>
										<div class="form-group bmd-form-group">
											<div class="input-group">
												<div class="input-group-prepend">
													<div class="input-group-text">
														<i class="material-icons">people</i>
													</div>
												</div>
												<input type="text" class="form-control" id="login"
													name="login" placeholder="Login" required>
											</div>
										</div>

										<div class="form-group bmd-form-group">
											<div class="input-group">
												<div class="input-group-prepend">
													<div class="input-group-text">
														<i class="material-icons">lock_outline</i>
													</div>
												</div>
												<input type="password" placeholder="Senha" id="senha"
													name="senha" class="form-control" required>
											</div>
										</div>
									</div>
									<div class="modal-footer justify-content-center">
										<button onclick="realizarLogin()" class="btn btn-primary btn-link btn-wd btn-lg preto" style="color: #fff">
											Acessar
										</button>
									</div>
								</form>
							</div>
						</div>
						<div class="tab-pane" id="registrar">
							<div class="modal-body">
								<form class="form" method="post" autocomplete="off" enctype="multipart/form-data" id="RegisterForm">
									<div class="card-body">
										<div class="form-group bmd-form-group">
											<div class="avatar-upload">
												<div class="avatar-edit">
													<input type='file' id="imageUpload" accept=".png, .jpg, .jpeg" name="imageUpload"/>
													<label for="imageUpload"></label>
												</div>
												<div class="avatar-preview">
													<div id="imagePreview"
														style="background-image: url(resources/img/user.jpg);">
													</div>
												</div>
											</div>
										</div>
										<p id="msgR"></p>
										<div class="form-group bmd-form-group">
											<div class="input-group">
												<div class="input-group-prepend">
													<div class="input-group-text">
														<i class="material-icons">people</i>
													</div>
												</div>
												<input type="text" class="form-control" id="loginR"
													name="loginR" placeholder="Login" required>
											</div>
										</div>

										<div class="form-group bmd-form-group">
											<div class="input-group">
												<div class="input-group-prepend">
													<div class="input-group-text">
														<i class="material-icons">lock_outline</i>
													</div>
												</div>
												<input type="password" placeholder="Senha" id="senhaR"
													name="senhaR" class="form-control" required>
											</div>
										</div>

										<div class="form-group bmd-form-group">
											<div class="input-group">
												<div class="input-group-prepend">
													<div class="input-group-text">
														<i class="material-icons">face</i>
													</div>
												</div>
												<input type="text" class="form-control" id="nomeR"
													name="nomeR" placeholder="Nome" required>
											</div>
										</div>
										
										<div class="form-group bmd-form-group">
											<div class="input-group">
												<div class="input-group-prepend">
													<div class="input-group-text">
														<i class="material-icons">phone</i>
													</div>
												</div>
												<input type="text" class="form-control" id="telefoneR"
													name="telefoneR" placeholder="Telefone" required>
											</div>
										</div>

										<div class="form-group bmd-form-group">
											<div class="input-group">
												<div class="input-group-prepend">
													<div class="input-group-text">
														<i class="material-icons">email</i>
													</div>
												</div>
												<input type="email" class="form-control" id="emailR"
													name="emailR" placeholder="Email" required>
											</div>
										</div>

										<div class="form-check">
											<label class="form-check-label"> <input
												class="form-check-input" type="checkbox" value=""> <span
												class="form-check-sign"> <span class="check"></span>
											</span>Eu concordo com os <a href="#something">termos e condições</a>.
											</label>
										</div>
									</div>
									<div class="modal-footer justify-content-center">
										<input type="button" onclick="realizarCadastro()"
											class="btn btn-primary btn-link btn-wd btn-lg preto"
											value="Cadastrar" style="color: #fff">
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function realizarLogin() {
		$("#msg").css('color', '#999');
		if($('#login').val() == '') {
			$("#msg").html("&times; Informe o Login");
			$("#msg").css('color', 'red');
			$("#modalShake").effect('shake');
			return false;
		} else if($('#senha').val() == ''){
			$("#msg").html("&times; Informe a Senha");
			$("#msg").css('color', 'red');
			$("#modalShake").effect('shake');
			return false;
		}
		$.ajax({
			url : "LoginServlet",
			type : 'post',
			data : {
				login : $('#login').val(),
				senha : $('#senha').val()
			},
			beforeSend : function() {
				$("#msg").html("PROCESSANDO...");
			}
		}).done(function(msg) {
			if (msg == 'OK') {
				window.location = window.location.href;
			} else {
				//var jsonobj = JSON.parse(msg);
				//$("#msg").html(jsonobj.message);
				$("#msg").html("&times; " + msg);
				$("#msg").css('color', 'red');
				$("#modalShake").effect('shake');
			}
		}).fail(function(jqXHR, textStatus, msg) {
			bootbox.alert(msg);
		});
	}

	function realizarCadastro() {
		$("#msgR").css('color', '#999');
		if($('#loginR').val() == '') {
			$("#msgR").html("&times; Informe o Login");
			$("#msgR").css('color', 'red');
			$("#modalShake").effect('shake');
			return false;
		} else if($('#senhaR').val() == ''){
			$("#msgR").html("&times; Informe a Senha");
			$("#msgR").css('color', 'red');
			$("#modalShake").effect('shake');
			return false;
		} else if($('#nomeR').val() == ''){
			$("#msgR").html("&times; Informe o Nome");
			$("#msgR").css('color', 'red');
			$("#modalShake").effect('shake');
			return false;
		} else if($('#telefoneR').val() == ''){
			$("#msgR").html("&times; Informe o Telefone");
			$("#msgR").css('color', 'red');
			$("#modalShake").effect('shake');
			return false;
		} else if($('#emailR').val() == ''){
			$("#msgR").html("&times; Informe o Email");
			$("#msgR").css('color', 'red');
			$("#modalShake").effect('shake');
			return false;
		}
		 
		var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if (!filter.test($('#emailR').val())) {
			$("#msgR").html("&times; Por favor, informe um email válido.");
			$("#emailR").css('color', 'red');
			$("#modalShake").effect('shake');
		    return false;
		}
		
		var form = $('#RegisterForm')[0];
		var data = new FormData(form);
		
		$.ajax({
			url : "RegisterServlet",
			type : 'post',
			enctype: 'multipart/form-data',
	        processData: false,  // Important!
	        contentType: false,
	        cache: false,
	        data: data,
			beforeSend : function() {
				$("#msgR").html("PROCESSANDO...");
			}
		}).done(function(msg) {
			if (msg == 'OK') {
				window.location = window.location.href;
			} else {
				$("#msgR").html("&times; " + msg);
				$("#msgR").css('color', 'red');
				$("#modalShake").effect('shake');
			}
		}).fail(function(jqXHR, textStatus, msg) {
			bootbox.alert(msg);
		});
	}
	
	$(document).ready(function(){
		var SPMaskBehavior = function (val) {
			return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
		},
		spOptions = {
			onKeyPress: function(val, e, field, options) {
				field.mask(SPMaskBehavior.apply({}, arguments), options);
		  	}
		};
		$('#telefoneR').mask(SPMaskBehavior, spOptions);
  	});
	
	function readURL(input) {
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();
	        reader.onload = function(e) {
	            $('#imagePreview').css('background-image', 'url('+e.target.result +')');
	            $('#imagePreview').hide();
	            $('#imagePreview').fadeIn(650);
	        }
	        reader.readAsDataURL(input.files[0]);
	    }
	}
	$("#imageUpload").change(function() {
	    readURL(this);
	});
</script>