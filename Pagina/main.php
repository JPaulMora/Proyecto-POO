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
    <link href="bootstrap.min.css" rel="stylesheet">
    <link href="main.css" rel="stylesheet">

    <link href="font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">



    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>





</head>

<body id="page-top" class="common-font-color-white width-100">
<div class="width-100">
    <?php include 'database.php';

    echo("Ejecutando...");
    try{
        echo("Trying...");

        $carnet=$_GET["carnet"];
        $pw=$_GET["pw"];
        echo("Trying...2");
        $pdo = Database::connect();
        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        $sql = "SELECT * FROM Clientes ";
        $result=$pdo->query($sql);
        if(in_array($carnet, $result) and in_array($pw, $result)){
            console.log("login exitoso");
        } elseif (result==null){
            header("Location: main.php");
        }
    }catch (Exception $e){
        
        console.log($e);
    }
    ?>

    <!-- Navigation -->
    <nav id="mainNav" class="navbar navbar-default navbar-fixed-top color-body common-font-color-white">
        <div class="container padding-0 ">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header ">
                <a class="navbar-brand common-font-color-white common-font-size-lg cleanup-link" href="#page-top">CobrosUVG</a>
            </div>
        </div>
    </nav>

    <!-- Header -->
    <header class="color-uvg">
        <div class="container padding-0">
            <div class="row-fluid padding-0">
                <div class="col-lg-12 padding-0  text-center common-margin-top-30">
                    <img class=" common-margin-top-80" src="img/portfolio/uvg.png" alt="">

                </div>
                <div class="col-lg-12 padding-0 text-center common-margin-top-30">
                    <span class="common-font-size-xl common-font-weight-bold common-font-line-height-xl">Sistema de cobros UVG</span>
                    <br>
                    <span class="common-font-size-lg common-font-line-height-xl">Que vas a comer hoy?</span>
                </div>

            </div>
        </div>
    </header>


    <!-- Portfolio Grid Section -->
    <section id="portfolio" class="common-margin-top-30 common-margin-bottom-70">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2>Opciones:  </h2>

                </div>
            </div>
            <div class="row">
                <div class="col-sm-4 portfolio-item">
                    <a href="" class="cleanup-link text-center" data-toggle="modal" data-target="#myModal3">

                        <img src="img/portfolio/cooker.png"  class="img-responsive" style="padding-left:45px;">
                    </a>
                </div>
                <div class="col-sm-4 portfolio-item">
                    <a href="" class="cleanup-link text-center" data-toggle="modal" data-target="#myModal1">

                        <img src="img/portfolio/padnote.png" class="img-responsive" style="padding-left:45px;">
                    </a>
                </div>
                <div class="col-sm-4 portfolio-item">
                    <a href="" class="cleanup-link text-center" data-toggle="modal" data-target="#myModal2">

                        <img src="img/portfolio/restaurant-app.png"  style="padding-left:45px;">
                    </a>
                </div>
            </div>
        </div>
    </section>
    <div class="container-fluid" style="padding-bottom: 30px">
        <div class="row">
            <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 col-lg-offset-4 col-md-offset-4">
                <button  style="width:100%; background-color:#2aa22a" id="Salir" class="btn btn-primary center-block" ><a class=" common-font-color-white cleanup-link" href = "logout.php">Salir</a></button>

            </div>

        </div>

    </div>

    <!-- Footer -->
    <footer class="text-center color-footer">
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
        <a class="btn btn-primary cleanup-link" href="#page-top">
            <i class="fa fa-chevron-up"></i>
        </a>
    </div>

    <!-- Portfolio Modals -->

    <div class="modal fade bs-example-modal-lg common-font-color-black  " id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel3">Perfíl del estudiante</h4>
                </div>
                <div class="modal-body">
                    <div class="container-fluid padding-0">
                        <div class="row padding-0">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-0">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-0 text-center">
                                    <?php

                                    $pdo = Database::connect();
                                    $sql = 'SELECT * FROM Transacciones ORDER BY id DESC LIMIT BY 10';
                                    $resultado=$pdo->query($sql);

                                    ?>

                                </div>
                                <div class="col-sm-12 col-md-4 col-xs-12 col-lg-3 text-center ">
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-0 common-font-weight-bold">
                                        Nombre
                                    </div>
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-0">
                                        asdasd
                                    </div>
                                </div>
                                <div class="col-sm-12 col-md-4 col-xs-12 col-lg-3 text-center">
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-0 common-font-weight-bold">
                                        Carnet
                                    </div>
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-0">
                                        ######
                                    </div>
                                </div>
                                <div class="col-sm-12 col-md-4 col-xs-12 col-lg-3 text-center">
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-0 common-font-weight-bold">
                                        Saldo restante
                                    </div>
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-0">
                                        asdasd
                                    </div>
                                </div>
                                <div class="col-sm-12 col-md-4 col-xs-12 col-lg-3 text-center">
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-0 common-font-weight-bold">
                                        Último consumo
                                    </div>
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-0">
                                        asdasd
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>


    <div class="modal fade bs-example-modal-lg common-font-color-black  " id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Hacer un consumo</h4>
                </div>
                <div class="modal-body">
                    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                        <div class="panel panel-default">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">

                                <div class="panel-heading" role="tab" id="headingOne">
                                    <h4 class="panel-title common-font-color-black">
                                        Café Gitane

                                    </h4>
                                </div>
                            </a>
                            <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                                <div class="panel-body">
                                    <form action="create.php" method="get">
                                        <div class="form-group">
                                            <label >Ingrese el nombre del producto a comprar</label>
                                            <input type="text" class="form-control" name="producto">
                                        </div>
                                        <div class="form-group hidden">
                                            <input type="number" class="hidden form-control" name="rest" value="1">
                                        </div>

                                        <button type="submit" class="btn btn-default">Comprar</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                <div class="panel-heading" role="tab" id="headingTwo">
                                    <h4 class="panel-title common-font-color-black">
                                        Bagel factory
                                    </h4>
                                </div>
                            </a>
                            <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                                <div class="panel-body">
                                    <form action="create.php" method="get">
                                        <div class="form-group">
                                            <label >Ingrese el nombre del producto a comprar</label>
                                            <input type="text" class="form-control" name="producto">
                                        </div>
                                        <div class="form-group hidden">
                                            <input type="number" class="hidden form-control" name="rest" value="2">
                                        </div>

                                        <button type="submit" class="btn btn-default">Comprar</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                <div class="panel-heading" role="tab" id="headingThree">
                                    <h4 class="panel-title common-font-color-black">
                                        Go Green
                                    </h4>
                                </div>
                            </a>
                            <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                                <div class="panel-body">
                                    <form action="create.php" method="get">
                                        <div class="form-group">
                                            <label >Ingrese el nombre del producto a comprar</label>
                                            <input type="text" class="form-control" name="producto">
                                        </div>
                                        <div class="form-group hidden">
                                            <input type="number" class="hidden form-control" name="rest" value="3">
                                        </div>

                                        <button type="submit" class="btn btn-default">Comprar</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade bs-example-modal-lg common-font-color-black  " id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel2">Consumos recientes</h4>
                </div>
                <div class="modal-body">
                    <div class="col-lg-12 text-center">
                        <table class="table ">
                            <thead>
                            <tr class="text-center">
                                <th>Fecha</th>
                                <th>Producto</th>
                                <th>$$$</th>
                                <th>Donde?</th>
                            </tr>
                            </thead>
                            <tbody>
                            <?php

                            $pdo = Database::connect();
                            $sql = 'SELECT * FROM Transacciones ORDER BY id DESC LIMIT BY 10';
                            foreach ($pdo->query($sql) as $row) {
                                echo '<tr>';
                                echo '<td>'. $row['fecha'] . '</td>';
                                echo '<td>'. $row['descripcion'] . '</td>';
                                echo '<td>'. $row['monto'] . '</td>';
                                echo '<td>'. $row['establecimiento'] . '</td>';
                                echo '</tr>';
                            }
                            Database::disconnect();
                            ?>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

</div>







<!-- Bootstrap Core JavaScript -->
<script src="bootstrap.min.js"></script>



</body>

</html>
