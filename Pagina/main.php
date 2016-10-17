<?php
include('session.php');
?>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Sistema de cobros Uvg</title>

    <!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Theme CSS -->
    <link href="css/freelancer.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">

    <script src="https://code.jquery.com/jquery-1.12.4.js" type="text/javascript"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <!-- Jquery-UI--->
    <link href="css/jquery-ui.css" rel="stylesheet">

    <script>

        function getQueryParams(qs) {
            qs = qs.split("+").join(" ");
            var params = {},
                tokens,
                re = /[?&]?([^=]+)=([^&]*)/g;

            while (tokens = re.exec(qs)) {
                params[decodeURIComponent(tokens[1])]
                    = decodeURIComponent(tokens[2]);
            }

            return params;
        }
        function getNombre(array){
            //aca iria un query a la base de datos para determinar el nombre del usuario
            if(array["email"]==1234){
                return "Ejemplo1";
            }else if(array["email"]==15246){
                return "Byron Mota";
            }else if(array["email"]==151126){
                return "asdaksmdaskldm";
            }
        }

        function getSaldo(array){
            //aca iria un query a la base de datos para determinar el saldo del usuario
            if(array["email"]==1234){
                return "100.00";
            }else if(array["email"]==15246){
                return "250.00";
            }else if(array["email"]==151126){
                return "50.00";
            }
        }

        function getConsumo(array){
            //aca iria un query a la base de datos para determinar el ultimo consumo del usuario
            if(array["email"]==1234){
                return "Unos deliciosos tacos";
            }else if(array["email"]==15246){
                return "Latte fr�o 16oz";
            }else if(array["email"]==151126){
                return "Galleta de Chocolate";
            }
        }

        var $_GET = getQueryParams(document.location.search);
        function setValues($_GET){
            $(document).ready(function(){
                $("#nombre").text(getNombre($_GET));
                $("#carnet").text($_GET["email"]);
                $("#saldo").text(getSaldo($_GET));
                $("#consumo").text(getConsumo($_GET));
            });
        }
        setValues($_GET);

        function comprar(){
            var consumoR=$('#comida').val();
            $("#consumo").text(consumoR);
            $.("#comida").text("");
            alert("Consumo agregado con exito");
        }

        $(document).ready(function(){
            $("#validar").click(function(){
                comprar();
                $('.modal').trigger("click");

            });
        });



    </script>


</head>

<body id="page-top" class="index">


<!-- Navigation -->
<nav id="mainNav" class="navbar navbar-default navbar-fixed-top navbar-custom">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header page-scroll">
            <a class="navbar-brand" href="#page-top">CobrosUVG</a>
        </div>
    </div>
</nav>

<!-- Header -->
<header>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="row">
                    <img class="img-responsive" src="img/portfolio/uvg.png" alt="">
                </div>
                <div class="intro-text">
                    <span class="name">Sistema de cobros UVG</span>
                    <span class="skills">Que vas a comer hoy?</span>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- Portfolio Grid Section -->
<section id="portfolio">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h2>Opciones:  </h2>
                <hr class="star-primary">
            </div>
        </div>
        <div class="row">
            <div class="col-sm-4 portfolio-item">
                <a href="#portfolioModal1" class="portfolio-link" data-toggle="modal">
                    <div class="caption">
                        <div class="caption-content">
                            <h3><strong>Perfil</strong></h3>
                        </div>
                    </div>
                    <img src="img/portfolio/cooker.png" class="img-responsive" style="padding-left:45px;">
                </a>
            </div>
            <div class="col-sm-4 portfolio-item">
                <a href="#portfolioModal2" class="portfolio-link" data-toggle="modal">
                    <div class="caption">
                        <div class="caption-content">
                            <h3><strong>Consumos</strong></h3>
                        </div>
                    </div>
                    <img src="img/portfolio/padnote.png" class="img-responsive" style="padding-left:45px;">
                </a>
            </div>
            <div class="col-sm-4 portfolio-item">
                <a href="#portfolioModal3" class="portfolio-link" data-toggle="modal">
                    <div class="caption">
                        <div class="caption-content">
                            <h3><strong>Hacer un consumo</strong></h3>
                        </div>
                    </div>
                    <img src="img/portfolio/restaurant-app.png" class="img-responsive" style="padding-left:45px;">
                </a>
            </div>
        </div>
    </div>
</section>

<!-- Footer -->
<footer class="text-center">
    <div class="footer-above">
        <div class="container">
            <div class="row">

                <div class="footer-col col-md-4">
                    <h3>Acerca del equipo:</h3>
                    <h4>Integrantes: </h4>
                    </br>
                    <div class="text-left">
                        <ul>
                            <li><h5>Pablo Rene Arellano</h5></li>
                            <li><h5>Jose Rodrigo Corona</h5></li>
                            <li><h5>Fernando Gabriel Mendez</h5></li>
                            <li><h5>Juan Pablo Mora</h5></li>
                            <li><h5>Byron Andres Mota</h5></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="footer-below">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    SistemaCobrosUVG &copy; POO 2016
                </div>
            </div>
        </div>
    </div>
</footer>

<!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
<div class="scroll-top page-scroll hidden-sm hidden-xs hidden-lg hidden-md">
    <a class="btn btn-primary" href="#page-top">
        <i class="fa fa-chevron-up"></i>
    </a>
</div>

<!-- Portfolio Modals -->
<div class="portfolio-modal modal fade" id="portfolioModal1" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-content">
        <div class="close-modal" data-dismiss="modal">
            <div class="lr">
                <div class="rl">
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2">
                    <div class="modal-body">
                        <h2>Perfil</h2>
                        <hr class="star-primary">
                        <img src="img/portfolio/user.png" class="img-responsive img-centered" alt="">
                        <ul class="list-inline item-details">
                            <li>Nombre:
                                <strong id="nombre"><?php //get de la base de datos para el nombre del usuario?></strong>
                            </li>
                            <li>Carnet:
                                <strong id="carnet"><?php //get de la base de datos para el carnet?></strong>
                            </li>
                            <li>Saldo Disponible:
                                <strong id="saldo"><?php //get de la base de datos, llamada de la funcion saldo()?></strong>
                            </li>
                            <li>Consumo mas Reciente:
                                <strong id="consumo"><?php //get de la base de datos para el ultimo consumo?></strong>
                            </li>
                        </ul>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="portfolio-modal modal fade" id="portfolioModal2" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-content">
        <div class="close-modal" data-dismiss="modal">
            <div class="lr">
                <div class="rl">
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="modal-body">
                        <h2>Consumos:</h2>
                        <div class="container" style="">
                            <table class="table table-condensed">
                                <thead>
                                <tr>
                                    <th><span class="glyphicon glyphicon-calendar"></span></th>
                                    <th><span class="glyphicon glyphicon-ice-lolly-tasted"></span></th>
                                    <th><img src="img/portfolio/money.png" style="margin-bottom:0px;"></th>
                                    <th><span class="glyphicon glyphicon-shopping-cart"></span></th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>10/10/10</td>
                                    <td>Cappuccino 12</td>
                                    <td>14.0</td>
                                    <td>GTN</td>
                                </tr>
                                <tr>
                                    <td>00/00/00</td>
                                    <td>Taco</td>
                                    <td>5</td>
                                    <td>GOG</td>
                                </tr>
                                <tr>
                                    <td>00/00/00</td>
                                    <td>Burrito</td>
                                    <td>7.0</td>
                                    <td>BGL</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="portfolio-modal modal fade" id="portfolioModal3" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-content">
        <div class="close-modal" data-dismiss="modal">
            <div class="lr">
                <div class="rl">
                </div>
            </div>
        </div>

        <div class="container">
            <div class="row" >
                <div class="col-lg-12">
                    <div class="modal-body">
                        <h2>Realizar un Consumo</h2>
                        <div id="accordion" role="tablist" aria-multiselectable="true" style="margin-top:20px;  ">
                            <div class="panel panel-default">
                                <div class="panel-heading" role="tab" id="headingOne">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                            Caf� Gitane
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                                    <div id="gitane">
                                        <input  type="text" class="form-control" placeholder="Escriba el producto que desea" id="comida" >
                                        <button  id="validar" class="btn btn-primary center-block" >Comprar</button>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading" role="tab" id="headingTwo">
                                    <h4 class="panel-title">
                                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                            GoGreen
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                                    Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading" role="tab" id="headingThree">
                                    <h4 class="panel-title">
                                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                            Bagel Factory
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                                    Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>




<!-- jQuery -->




<!-- Bootstrap Core JavaScript -->
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>


<!-- Plugin JavaScript -->

<!-- Contact Form JavaScript -->
<script src="js/jqBootstrapValidation.js"></script>
<script src="js/contact_me.js"></script>

<!-- Theme JavaScript -->
<script src="js/freelancer.min.js"></script>

</body>

</html>