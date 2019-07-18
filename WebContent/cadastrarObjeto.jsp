<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>
<head>
<title>Ezzie - Cadastrar Objeto</title>
<jsp:include page="includes/head.jsp" />
</head>
<body class="profile-page sidebar-collapse bg-ezzie">

	<div class="loader"></div>

	<jsp:include page="includes/navbar.jsp" />
	<jsp:include page="includes/login.jsp" />

	<div class="page-header" data-parallax="true">
		<div class="container">
			<div class="row">
				<div class="col-md-8 ml-auto mr-auto">
					<div class="brand text-center">
						<img src="resources/img/Ezzie-logo.png" class="img-fluid" alt="Ezzie" width="150px">
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="main main-raised">
		<div class="container">
			<div class="section">
				<h2>
					<small class="text-muted">Cadastre um objeto!</small>
				</h2>
				<div class="row">
					<div class="col-sm">
						<form action="ObjetoServlet" method="post" autocomplete="off" enctype="multipart/form-data">
							<div class="form-group">
								<label for="inputEmail4">Titulo</label>
								<input type="text" class="form-control" id="titulo" name="titulo">
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="exampleFormControlSelect1">Selecione o Tipo</label>
									<select class="form-control selectpicker"
										data-style="btn btn-link" id="tipo" name="tipo">
										<option></option>
										<option>Documentos Pessoais</option>
										<option>Animais (Cães e Gatos, Etc)</option>
										<option>Celulares / Smartphones / Etc</option>
										<option>Veículos (Carros, Motos)</option>
										<option>Chaves e Chaveiros</option>
										<option>Eletrônicos / Computadores / Tablets, Etc</option>
										<option>Canetas / Relógio / Joias, Alianças, Cordões, Etc</option>
										<option>Carteiras / Bolsas e Malas</option>
										<option>Outros</option>
										<option>Roupas e Calçados</option>
										<option>Pessoas Desaparecidas</option>
									</select>
								</div>
								<div class="form-group col-md-6">
									<label for="exampleFormControlSelect1">Selecione o Situação</label>
									<select class="form-control selectpicker"
										data-style="btn btn-link" id="situacao" name="situacao">
										<option></option>
										<option>Achado</option>
										<option>Perdido</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="exampleFormControlTextarea1">Descrição</label>
								<textarea class="form-control" id="descricao" name="descricao"
									rows="3"></textarea>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="inputCity">Cidade</label>
									<input type="text" class="form-control" id="cidade" name="cidade" placeholder="Selecione no mapa" readonly>
								</div>
								<div class="form-group col-md-6">
									<label for="inputCity">Estado</label>
									<input type="text" class="form-control" id="estado" name="estado" placeholder="Selecione no mapa" readonly>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label>Imagem</label><br/>
									<span class="btn btn-default btn-file">
										<input type="file" id="imgInp" name="imgInp" style="z-index:10">
										Procurar...
									</span>
								</div>
								<div class="form-group col-md-6">
									<div class="card" style="margin-top: 0">
										<img id='img-upload' />
									</div>

								</div>
							</div>
							<input type="hidden" name="latLng" value="">
							<button type="submit" class="btn btn-primary preto">Salvar</button>
						</form>
					</div>
					<div class="col-sm">
						<input id="pac-input" name="location" class="form-control"
							type="text" placeholder="Pesquisar"
							style="margin: 15px; padding: 5px; background-color: #fff; border: 1px solid gray; border-radius: 5px; width: 50%">
						<div id="map" style="margin-top: 0"></div>
						<div style="display: none">
							<div class="controls zoom-control">
								<button class="zoom-control-in" title="Zoom In">+</button>
								<button class="zoom-control-out" title="Zoom Out">&minus;</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="includes/footer.jsp" />
	<jsp:include page="includes/scriptsFooter.jsp" />
	<script type="text/javascript">
	$(function() {
		function initMap() {
			// Pega o elemento onde o mapa ficará
			var mapCanvas = document.getElementById('map');
			// Coordenadas do Brasil
			var BRASIL = {
				lat : -13.83,
				lng : -48.02
			};

			// Limites do mapa do Brasil
			var BRASIL_BOUNDS = {
				north : 5.50,
				south : -34.00,
				west : -74.17,
				east : -34.53,
			};

			// Definição das opções do mapa
			var mapOptions = {
				center : BRASIL,
				restriction : {
					latLngBounds : BRASIL_BOUNDS,
					strictBounds : false,
				},
				zoom : 12,
				panControl : false,
				disableDefaultUI : true,
				mapTypeId : google.maps.MapTypeId.ROADMAP
			}

			// Inicia o mapa
			var map = new google.maps.Map(mapCanvas, mapOptions);
			
			// Adiciona o botão de zoom no mapa
			document.querySelector('.zoom-control-in').onclick = function() {
				map.setZoom(map.getZoom() + 1);
			};
			document.querySelector('.zoom-control-out').onclick = function() {
				map.setZoom(map.getZoom() - 1);
			};
			map.controls[google.maps.ControlPosition.RIGHT_BOTTOM].push(document
					.querySelector('.zoom-control'));

			// Define a imagem do marcador
			var markerImage = 'resources/img/marker.png';
			
			// Tente pega a geolocalizacao do HTML5.
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(function(position) {
					var pos = {
						lat : position.coords.latitude,
						lng : position.coords.longitude
					};
					map.setCenter(pos);
				});
			}

			// Marca no mapa ao clicar com o mouse
			var markerClick;
			google.maps.event.addListener(map, 'click', function(event) {
				if (markerClick == null) {
					markerClick = new google.maps.Marker({
						position : event.latLng,
						animation : google.maps.Animation.DROP,
						icon : markerImage,
						map : map
					});
				} else {
					markerClick.setPosition(event.latLng);
				}
				$("input[name='cidade']").val("Carregando...");
				$("input[name='estado']").val("Carregando...");
				map.panTo(event.latLng);
				geocoder = new google.maps.Geocoder();
				geocoder.geocode({'latLng' : event.latLng},	function(results, status) {
					if (status == google.maps.GeocoderStatus.OK) {
						if (results[1]) {
							for (var i = 0; i < results.length; i++) {
								if (results[i].types[0] === "administrative_area_level_2") {
									var cidade = results[i].address_components[0].short_name;
								} else if (results[i].types[0] === "administrative_area_level_1") {
									var estado = results[i].address_components[0].short_name;
								} else if (results[i].types[0] === "country") {
									var pais = results[i].address_components[0].short_name;
								}
							}
						if(pais == 'BR') {
							$("input[name='cidade']").val(cidade);
							$("input[name='estado']").val(estado);
							$("input[name='latLng']").val(event.latLng);
						}
						} else {
							console.log("No reverse geocode results.");
							$("input[name='cidade']").val("Sem resultados, tente novamente...");
							$("input[name='estado']").val("Sem resultados, tente novamente...");
						}
					} else {
						console.log("Geocoder failed: "	+ status);
						$("input[name='cidade']").val("Sem resultados, tente novamente...");
						$("input[name='estado']").val("Sem resultados, tente novamente...");
					}
				});
			});
			
			// Create the search box and link it to the UI element.
			var input = document.getElementById('pac-input');
			var searchBox = new google.maps.places.SearchBox(input);
			map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

			// Bias the SearchBox results towards current map's viewport.
			map.addListener('bounds_changed', function() {
				searchBox.setBounds(map.getBounds());
			});

			// Listen for the event fired when the user selects a prediction and
			// retrieve
			// more details for that place.
			searchBox.addListener('places_changed', function() {
				var places = searchBox.getPlaces();

				if (places.length == 0) {
					return;
				}

				// For each place, get the icon, name and location.
				var bounds = new google.maps.LatLngBounds();
				places.forEach(function(place) {
					if (!place.geometry) {
						console.log("Returned place contains no geometry");
						return;
					}

					if (place.geometry.viewport) {
						// Only geocodes have viewport.
						bounds.union(place.geometry.viewport);
					} else {
						bounds.extend(place.geometry.location);
					}
				});
				map.fitBounds(bounds);
			});
			
			var styles = [{"featureType": "landscape", "stylers": [{"saturation": -100}, {"lightness": 65}, {"visibility": "on"}]}, {"featureType": "poi", "stylers": [{"saturation": -100}, {"lightness": 51}, {"visibility": "simplified"}]}, {"featureType": "road.highway", "stylers": [{"saturation": -100}, {"visibility": "simplified"}]}, {"featureType": "road.arterial", "stylers": [{"saturation": -100}, {"lightness": 30}, {"visibility": "on"}]}, {"featureType": "road.local", "stylers": [{"saturation": -100}, {"lightness": 40}, {"visibility": "on"}]}, {"featureType": "transit", "stylers": [{"saturation": -100}, {"visibility": "simplified"}]}, {"featureType": "administrative.province", "stylers": [{"visibility": "off"}]}, {"featureType": "water", "elementType": "labels", "stylers": [{"visibility": "on"}, {"lightness": -25}, {"saturation": -100}]}, {"featureType": "water", "elementType": "geometry", "stylers": [{"hue": "#ffff00"}, {"lightness": -25}, {"saturation": -97}]}];
			map.set('styles', styles);
		}
		
		google.maps.event.addDomListener(window, 'load', initMap);
		
	});
	</script>
	<script type="text/javascript">
		$(document).ready(
				function() {
					$(document).on('change', '.btn-file :file', function() {
						var input = $(this), label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
						input.trigger('fileselect', [ label ]);
					});

					$('.btn-file :file').on('fileselect', function(event, label) {
						var input = $(this).parents('.input-group').find(':text'), log = label;
					});
					
					function readURL(input) {
						if (input.files && input.files[0]) {
							var reader = new FileReader();
							reader.onload = function(e) {
								$('#img-upload').attr('src', e.target.result);
							}
							reader.readAsDataURL(input.files[0]);
						}
					}
					$("#imgInp").change(function() {
						readURL(this);
					});
				});
	</script>
</body>
</html>