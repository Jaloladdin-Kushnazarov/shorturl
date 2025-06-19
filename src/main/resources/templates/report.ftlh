<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>Urls</title>

    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Open+Sans"/>

    <style>
        * {
            font-family: Arial, sans-serif;
        }

        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        .header {
            text-transform: uppercase;
        }

        tr:hover {
            background-color: coral;
        }
    </style>
</head>
<body>

<h1 class="header" style="font-size: 32px;">
    Dear <span style="color: forestgreen">${username}</span>, Weekly Report
    <span style="color: #ff8400;">${from} - ${to}</span>
</h1>

<h2>Total Weekly Generated URLs Count : ${count}</h2>

<#list dailyReports as daily_report>
    <hr/>
    <h2>${daily_report.dayName}</h2>
    <table>
        <thead>
        <tr>
            <th>#</th>
            <th>Description</th>
            <th>Path</th>
        </tr>
        </thead>
        <tbody>
        <#list daily_report.reports as url_report>
            <tr style="background-color: <#if url_report.expired>lightgray<#else>lightcoral</#if>;">
                <td>${url_report_index + 1}</td>
                <td><a href="${url_report.url}">${url_report.description}</a></td>
                <td><a href="${url_report.url}">${url_report.code}</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
    <h3>Total Daily Generated URLs Count: ${daily_report.count}</h3>
</#list>

</body>
</html>