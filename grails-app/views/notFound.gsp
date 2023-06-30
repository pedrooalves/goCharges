<!doctype html>
<html>
<head>
    <title>Page Not Found</title>
    <meta name="layout" content="main">
</head>
<body>
    <div id="content" role="main">
        <div class="container d-flex justify-content-center align-items-center mt-5">
            <div class="d-flex justify-content-center flex-column align-items-center">
                <div>
                    <h1 class="display-2 text-center m-0">Oops...</h1>
                    <h2 class="display-4 text-center m-0">Página não encontrada</h2>
                </div>

                <g:link class="mt-4" url="${request.getHeader('referer')}">
                    <button class="btn btn-lg px-4 btn-gogreen">
                        Voltar
                    </button>
                </g:link>
            </div>
        </div>
    </div>
</body>
</html>
