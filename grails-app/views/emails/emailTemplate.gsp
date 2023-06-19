<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        @media (max-width: 700px) {
            .container {
                width: 100%;
                padding-right: 15px;
                padding-left: 15px;
                margin-right: auto;
                margin-left: auto;
            }
        }

        @media (min-width: 700px) {
            .container {
                width: 50%;
                padding-right: 15px;
                padding-left: 15px;
                margin-right: auto;
                margin-left: auto;
            }
        }

        .card {
            position: relative;
            display: -ms-flexbox;
            display: flex;
            -ms-flex-direction: column;
            flex-direction: column;
            min-width: 0;
            word-wrap: break-word;
            background-color: #fff;
            background-clip: border-box;
            border: 1px solid rgba(0, 0, 0, 0.125);
            border-radius: 0.25rem;
        }

        .bg-gogreen {
            background-color: #88B337;
        }

        .text-gogreen {
            color: #88B337;
        }

        .justify-content-center {
            -ms-flex-pack: center !important;
            justify-content: center !important;
        }

        .text-white {
            color: #fff !important;
        }

        .text-center {
            text-align: center !important;
        }

        .d-flex {
            display: -ms-flexbox !important;
            display: flex !important;
        }

        .mb-3 {
            margin-bottom: 1rem !important;
        }

        .mt-3 {
            margin-top: 1rem !important;
        }

        .ml-5 {
            margin-left: 3rem !important;
            padding-right: 1rem !important;
        }

        .p-5 {
            padding: 3rem !important;
        }

        .display-4 {
            font-size: 3.5rem;
            padding: 1rem;
            font-weight: 300;
            line-height: 1.2;
        }

        .align-bottom {
            vertical-align: bottom !important;
        }

        .footer {
            padding-left: 3rem !important;
            padding-bottom: 2rem !important;
        }

        .text-footer {
            font-weight: bold;
            font-size: 1.10rem;
        }

        .bg-grey {
            background-color: lightgray;
        }

        table {
            padding-left: 3rem !important;
            padding-right: 1.5rem !important;
            border-collapse: separate;
            width: 100% !important;
        }

        table td {
            font-family: sans-serif;
            font-size: 14px;
            vertical-align: top;
        }
    </style>
</head>
<body>
    <div class="container d-flex justify-content-center mb-3">
        <div>
            <div class="card mb-3 mt-3 p-5 bg-gogreen text-center text-white">
                <span class="display-4 align-bottom">${mailSubject}</span>
            </div>

            <div class="card">
                <div>
                    <div class="text-gogreen">
                        <h2 class="ml-5">Olá, ${recipientEmail}!</h2>
                    </div>
                    <table>
                        <tbody>
                            <tr>
                                <td>
                                    <p>${mailBody}</p>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="card mb-3 mt-3 footer bg-grey">
                <div>
                    <br>
                    <span class="text-footer">Abraços,</span>
                    <br>
                    <span class="text-footer">Equipe GoCharges</span>
                </div>
            </div>
        </div>
    </div>
</body>
</html>