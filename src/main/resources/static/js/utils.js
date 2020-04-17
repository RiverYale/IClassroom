var contextPath = "/iclassroom";

function redrectTo(id, delay = 0) {
    setTimeout(function(){
        switch(id) {
            case 0:
                window.location.reload();
                break;
            case 1:
                window.location.href="login"; 
                break;
            case 2:
                window.location.href="main"; 
                break;
            case 3:
                window.location.href="reqs"; 
                break;
        }
    }, delay);
}