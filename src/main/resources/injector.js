var getHeadTag = document.getElementsByTagName('head')[0];
var newScriptTag = document.createElement('script');
newScriptTag.type='text/javascript';
newScriptTag.src='https://cdnjs.cloudflare.com/ajax/libs/axe-core/2.1.7/axe.min.js';
// adding <script> to <head>
getHeadTag.appendChild(newScriptTag);