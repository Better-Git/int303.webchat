$('#fox').css('visibility', 'hidden');
$('.button-collapse').sideNav({
    menuWidth: 240,
    edge: 'left',
    closeOnClick: true
});
$('.collapsible').collapsible();
$('.modal-trigger').leanModal();

var num1, num2, hd, uv;

$(function () {
    num1 = random(15), num2 = random(2);
    switch (num1) {
        case 0:
            hd = 'url(http://www.damncovers.com/wp-content/uploads/2015/01/Sleeping_Simpson-fb-cover.jpg) repeat 0% 50%/250px 75px';
            break;
        case 1:
            hd = 'url(http://images6.fanpop.com/image/polls/1247000/1247117_1373739687003_full.png?v=1373739703) repeat 0% 7%/240px 80px';
            break;
        case 2:
            hd = 'url(http://www.index.hr/images2/bart_simpson_skateboard_by_chrisy939-d4vgycf.jpg) repeat 0% 50%/250px 105px';
            break;
        case 3:
            hd = 'url(http://img05.deviantart.net/1e48/i/2012/130/5/a/yayyyyyyy__by_williamfreeman-d4z7cg5.png) repeat 0% 50%/150px 75px';
            break;
        case 4:
            hd = 'url(http://4.bp.blogspot.com/-k1M0W9HT9us/T1qg7C9J1eI/AAAAAAAAACs/StLB5-raJAs/s1600/1478746836-sara-vivas-bart-simpson-gamberro-ingenuo.jpg) repeat 0% 50%/125px 75px';
            break;
        case 5:
            hd = 'url(http://img02.deviantart.net/b75a/i/2012/025/d/3/why_you_little_______02_by_williamfreeman-d4nmwst.png) repeat 0% 50%/125px 65px';
            break;
        case 6:
            hd = 'url(http://orig04.deviantart.net/7cc3/f/2010/362/3/7/bart_3_by_jh622-d1ibv1u.png) repeat 0% 50%/100px 65px';
            break;
        case 7:
            hd = 'url(http://orig09.deviantart.net/34e5/f/2012/116/a/9/let_me_hold_your_hand_by_williamfreeman-d4xmsug.png) repeat 0% 50%/100px 75px';
            break;
        case 8:
            hd = 'url(http://www.cinepremiere.com.mx/assets/images/noticias/2012/11-noviembre/simpson.jpg) repeat 0% 95%/150px 95px';
            break;
        case 9:
            hd = 'url(http://www.affairesdegars.com/webroot/usr_img/2015-05/mai/semaine5/avenir.jpg) repeat 0% 50%/250px 90px';
            break;
        case 10:
            hd = 'url(https://danilasblog.files.wordpress.com/2011/06/simmonss.png) repeat 0% 50%/250px 75px';
            break;
        case 11:
            hd = 'url(http://www.audionews.com.br/slider/slide_topo/3.jpg) repeat 0% 50%/250px 97px';
            break;
        case 12:
            hd = 'url(http://cdn.foxplaybrasil.com.br/sites/foxplay-br/files/img/resized/1300x400/2600x800-series-los_simpson_nova.jpg?ver=c3e82624-ef2d-4018-ba18-498024ac9763) repeat 0% 50%/250px 75px';
            break;
        case 13:
            hd = 'url(https://postergully.files.wordpress.com/2014/04/01.jpg) repeat 0% 50%/250px 75px';
            break;
        case 14:
            hd = 'url(http://desktopwalls.net/wp-content/uploads/2014/07/Bart%20Simpson%20Cartoon%20Art%20Illustration%20Desktop%20Wallpaper.jpg) repeat 0% 50%/150px 75px';
            break;
    }
    switch (num2) {
        case 0:
            uv = 'url(http://www.cinepremiere.com.mx/assets/images/noticias/2012/11-noviembre/simpson.jpg) repeat 110% 90%/200px 120px';
            break;
        case 1:
            uv = 'url(http://desktopwalls.net/wp-content/uploads/2014/07/Bart%20Simpson%20Cartoon%20Art%20Illustration%20Desktop%20Wallpaper.jpg) repeat -825% 25%/250px 130px';
            break;
    }
});

function configure(hd, uv, css) {
    $('#hd').css('background', hd);
    $('#uv').css('background', uv);
    $('#css').removeAttr('href');
    $('#css').attr('href', css);
    $('#fox').css('visibility', 'hidden');
}

function random(int) {
    return Math.floor(Math.random() * int);
}

function theme(element) {
    switch (element.id) {
        case "clg":
            configure("", "#8BC34A", "https://code.getmdl.io/1.2.1/material.light_green-light_blue.min.css");
            break;
        case "cg":
            configure("", "#4CAF50", "https://code.getmdl.io/1.2.1/material.green-amber.min.css");
            break;
        case "cr":
            configure("", "#F44336", "https://code.getmdl.io/1.2.1/material.red-blue.min.css");
            break;
        case "cdo":
            configure("", "#FF5722", "https://code.getmdl.io/1.2.1/material.deep_orange-light_green.min.css");
            break;
        case "cl":
            configure("", "#CDDC39", "https://code.getmdl.io/1.2.1/material.lime-cyan.min.css");
            break;
        case "ct":
            configure("", "#009688", "https://code.getmdl.io/1.2.1/material.teal-amber.min.css");
            break;
        case "cp":
            configure("", "#E91E63", "https://code.getmdl.io/1.2.1/material.pink-cyan.min.css");
            break;
        case "cbr":
            configure("", "#795548", "https://code.getmdl.io/1.2.1/material.brown-pink.min.css");
            break;
        case "cy":
            configure("", "#FFEB3B", "https://code.getmdl.io/1.2.1/material.yellow-light_blue.min.css");
            break;
        case "cc":
            configure("", "#00BCD4", "https://code.getmdl.io/1.2.1/material.cyan-orange.min.css");
            break;
        case "cpu":
            configure("", "#9C27B0", "https://code.getmdl.io/1.2.1/material.purple-green.min.css");
            break;
        case "cgr":
            configure("", "#9E9E9E", "https://code.getmdl.io/1.2.1/material.grey-cyan.min.css");
            break;
        case "ca":
            configure("", "#FFC107", "https://code.getmdl.io/1.2.1/material.amber-light_blue.min.css");
            break;
        case "clb":
            configure("", "#03A9F4", "https://code.getmdl.io/1.2.1/material.light_blue-cyan.min.css");
            break;
        case "cdp":
            configure("", "#673AB7", "https://code.getmdl.io/1.2.1/material.deep_purple-yellow.min.css");
            break;
        case "cbart":
            configure(hd, uv, "https://code.getmdl.io/1.2.1/material.amber-orange.min.css");
            $('#fox').css('visibility', 'visible');
            break;
        case "co":
            configure("", "#FF9800", "https://code.getmdl.io/1.2.1/material.orange-light_green.min.css");
            break;
        case "cb":
            configure("", "#2196F3", "https://code.getmdl.io/1.2.1/material.blue-yellow.min.css");
            break;
        case "ci":
            configure("", "#3F51B5", "https://code.getmdl.io/1.2.1/material.indigo-green.min.css");
            break;
        case "cbg":
            configure("", "#607D8B", "https://code.getmdl.io/1.2.1/material.blue_grey-teal.min.css");
            break;
    }
//    document.cookie = "theme=" + element.id;
}