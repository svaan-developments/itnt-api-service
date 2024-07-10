<!doctype html>
<html lang = "en">
   <head>
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <link href="https://fonts.googleapis.com/css2?family=Rubik:ital,wght@0,300..900;1,300..900&display=swap" rel="stylesheet">
      <title>Simple Transactional Email</title>
      <style media="all" type="text/css">
@page{
size: 110mm 170mm;
margin: 0;
}
         /* -------------------------------------
         GLOBAL RESETS
         ------------------------------------- */

         body {
font-family: "Rubik", sans-serif;
-webkit-font-smoothing: antialiased;
font-size: 16px;
line-height: 1.3;
-ms-text-size-adjust: 100%;
-webkit-text-size-adjust: 100%;
}
         table {
border-collapse: separate;
mso-table-lspace: 0pt;
mso-table-rspace: 0pt;
width: 100%;
}
         table td {
font-family: "Rubik", sans-serif;
font-size: 16px;
vertical-align: top;
}
         /* -------------------------------------
         BODY & CONTAINER
         ------------------------------------- */
         body {
background-color: #f4f5f6;
margin: 0;
padding: 0;
}
.body {
background-color: #f4f5f6;
width: 100%;
}
.container {
margin: 0 auto !important;
max-width: 600px;
padding: 0;
padding-top: 24px;
width: 600px;
}
.content {
box-sizing: border-box;
display: block;
margin: 0 auto;
max-width: 600px;
padding: 50px;
}
/* -------------------------------------
HEADER, FOOTER, MAIN
-------------------------------------*/
.main {
background: rgba(255, 255, 255, 0.75);
border: 1px solid #eaebed;
border-radius: 16px;
width: 100%;
}
.wrapper {
box-sizing: border-box;
padding: 24px;
}
.footer {
clear: both;
padding-top: 24px;
text-align: center;
width: 100%;
}
.footer td,
.footer p,
.footer span,
.footer a {
color: #9a9ea6;
font-size: 16px;
text-align: center;
}
/* -------------------------------------TYPOGRAPHY
-------------------------------------*/
p {
font-family: "Rubik", sans-serif;
font-size: 16px;
font-weight: normal;
margin: 0;
margin-bottom: 16px;
}
a {
color: #0867ec;
text-decoration: underline;
}
/* -------------------------------------BUTTONS
-------------------------------------*/
.btn {
box-sizing: border-box;
min-width: 100% !important;
width: 100%;
}
.btn > tbody > tr > td {
padding-bottom: 16px;
}
.btn table {
width: 100%;
}
.btn table td {
background-color: #ffffff;
border-radius: 4px;
text-align: center;
}
.btn a {
background-color: #ffffff;
border: solid 2px #0867ec;
border-radius: 15px;
box-sizing: border-box;
color: #0867ec;
cursor: pointer;
display: inline-block;
font-size: 20px;
font-weight: bold;
margin: 0;
padding: 12px 24px;
text-decoration: none;
text-transform: capitalize;
}
.btn-primary table td {
background-color: transparent;
}
.btn-primary a {
background-color: #f14a56;
border-color: #f14a56;
color: #ffffff;
width: 80%;
font-size: 24px;
}
.vip {
background-color: #B91B58 !important;
border-color: #F14A56 !important;
}
.vvip {
background-color: #F14956 !important;
border-color: #F14956 !important;
}
.delegates {
background-color: #3B98E6 !important;
border-color: #3B98E6 !important;
}
@media all {
.btn-primary table td:hover {
background-color: transparent !important;
}
.btn-primary a:hover {
background-color: #000 !important;
border-color: #000 !important;
}
}
/* -------------------------------------OTHER STYLES THAT MIGHT BE USEFUL
-------------------------------------*/
.last {
margin-bottom: 0;
}
.first {
margin-top: 0;
}
.align-center {
text-align: center;
}
.align-right {
text-align: right;
}
.align-left {
text-align: left;
}
.text-link {
color: #0867ec !important;
text-decoration: underline !important;
}
.clear {
clear: both;
}
.mt0 {
margin-top: 0;
}
.mb0 {
margin-bottom: 0;
}
.preheader {
color: transparent;
display: none;
height: 0;
max-height: 0;
max-width: 0;
opacity: 0;
overflow: hidden;
mso-hide: all;
visibility: hidden;
width: 0;
}
.powered-by a {
text-decoration: none;
}
/* -------------------------------------RESPONSIVE AND MOBILE FRIENDLY STYLES
-------------------------------------*/
@media only screen and (max-width: 640px) {
.main p,
.main td,
.main span {
font-size: 16px !important;
}
.wrapper {
padding: 8px !important;
}
.content {
padding: 0 !important;
}
.container {
padding: 0 !important;
padding-top: 8px !important;
width: 100% !important;
}
.main {
border-left-width: 0 !important;
border-radius: 0 !important;
border-right-width: 0 !important;
}
.btn table {
max-width: 100% !important;
width: 100% !important;
}
.btn a {
font-size: 16px !important;
max-width: 100% !important;
width: 100% !important;
}
}
/* -------------------------------------PRESERVE THESE STYLES IN THE HEAD
-------------------------------------*/
@media all {
.ExternalClass {
width: 100%;
}
.ExternalClass,
.ExternalClass p,
.ExternalClass span,
.ExternalClass font,
.ExternalClass td,
.ExternalClass div {
line-height: 100%;
}
.apple-link a {
color: inherit !important;
font-family: inherit !important;
font-size: inherit !important;
font-weight: inherit !important;
line-height: inherit !important;
text-decoration: none !important;
}
#MessageViewBody a {
color: inherit;
text-decoration: none;
font-size: inherit;
font-family: inherit;
font-weight: inherit;
line-height: inherit;
}
}

</style>
<script>
document.addEventListener("DOMContentLoaded", function() {
const btn = document.querySelector(".dynamic-btn");
           const value = btn.textContent.trim();
            if (value === "VIP") {
btn.classList.add("vip");
} else if (value === "VVIP") {
btn.classList.add("vvip");
} else if (value === "DELEGATES") {
btn.classList.add("delegates");
} else {
btn.style.backgroundColor = "#F14A56"; // Default color
}
        });
    </script>
</head>
<body>
<table role = "presentation" border="0" cellpadding="0" cellspacing="0" class="body" style="background: url(https://svaantech.com/wp-content/uploads/2024/07/MainBG.svg) no-repeat center; background-size: cover; width: 416px; max-width: 416px; margin: 0 auto;height: 643px;">
         <tr>
            <td class="container">
               <div class="content" style="padding: 20px;">
                  <!-- START CENTERED WHITE CONTAINER -->
                  <span class="preheader" style="display: none; visibility: hidden;">This is preheader text. Some clients will show this text as a preview.</span>
                  <table role="presentation" border="0" cellpadding="0" cellspacing="0" class="main">
                     <!-- START MAIN CONTENT AREA -->
                     <tr style="display:block;">
                        <td class="wrapper" style="text-align: center; width: 100%;display:block;">
                           <p style="text-align: center;"><img src="https://svaantech.com/wp-content/uploads/2024/07/Logo.svg" alt="Logo" style="max-width: 100%;width: 60%; height: auto;"></p>
                           <h3 style="font-size: 22px;margin-top: 0px; margin-bottom: 10px; padding-bottom: 0; font-weight: 900;">${name}</h3>
                           <h5 style="font-size: 12px; margin-bottom: 0; padding-bottom: 0; font-weight: 900; margin-top: 0px;">${organization}</h5>
                           <h5 style="font-size: 12px; margin-bottom: 30px; padding-bottom: 0; font-weight: 900; margin-top: 0px;">${designation}</h5>
                           <div style=" border-radius: 10px;text-align: center; margin: auto;">
                              <table width="100%" height="100%">
                                 <tr>
                                    <td align="center" valign="middle">
                                     <img src="data:image/png;base64,${qrCodeBase64}" style="max-width: 100%; max-height: 100%; height: auto;width: 100px; height: 100px; width: auto;border-radius:15px">
                                    </td>
                                 </tr>
                              </table>
                           </div>
                               <img src="https://svaantech.com/wp-content/uploads/2024/07/location.svg" style="margin-top: 30px;width: 80%;">
                        </td>
                        <td></td>
                     </tr>
                     <!-- Equal Columns Row -->

                     <!-- END MAIN CONTENT AREA -->
                  </table>
               </div>
            </td>
         </tr>
         <tr>
            <td class="content-block">
               <table role="presentation" border="0" cellpadding="0" cellspacing="0" class="btn btn-primary">
                  <tbody>
                     <tr>
                        <td align="left">
                           <table role="presentation" border="0" cellpadding="0" cellspacing="0">
                              <tbody>
                                 <tr>
                                    <td> <a href="#" target="_blank" class="dynamic-btn" style="background-color: #F14956 !important;border-color: #F14956 !important;">${type}</a> </td>
                                 </tr>
                              </tbody>
                           </table>
                        </td>
                     </tr>
                  </tbody>
               </table>
            </td>
         </tr>
      </table>
      </td>
      </tr>
      <!-- END MAIN CONTENT AREA -->
      </table>
      <!-- START FOOTER -->
      <!-- END FOOTER -->
      <!-- END CENTERED WHITE CONTAINER --></div>
      </td>
      <td>&nbsp;</td>
      </tr>
      </table>
   </body>
</html>