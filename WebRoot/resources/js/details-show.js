var currentImage;
var currentIndex = -1;

//显示大图(参数index从0开始计数)
function showImage(index){

    //更新当前图片页码
    $(".CounterCurrent").html(index + 1);

    //隐藏或显示向左向右鼠标手势
    var len = $('#OriginalPic img').length;
    if(index == len - 1){
        $("#aNext").hide();
    }else{
        $("#aNext").show();
    }

    if(index == 0){
        $("#aPrev").hide();
    }else{
        $("#aPrev").show();
    }

    //显示大图            
    if(index < $('#OriginalPic img').length){
        var indexImage = $('#OriginalPic p')[index];

        //隐藏当前的图
        if(currentImage){
            if(currentImage != indexImage){
                $(currentImage).css('z-index', 2);  
                $(currentImage).fadeOut(0,function(){
                    $(this).css({'display':'none','z-index':1})
                });
            }
        }

        //显示用户选择的图
        $(indexImage).show().css({'opacity': 0.4});
        $(indexImage).animate({opacity:1},{duration:200});

        //更新变量
        currentImage = indexImage;
        currentIndex = index;

        //移除并添加高亮
        $('#ThumbPic img').removeClass('active');
        $($('#ThumbPic img')[index]).addClass('active');

        //设置向左向右鼠标手势区域的高度                        
        //var tempHeight = $($('#OriginalPic img')[index]).height();
        //$('#aPrev').height(tempHeight);
        //$('#aNext').height(tempHeight);                        
    }
}

//下一张
function ShowNext(){
    var len = $('#OriginalPic img').length;
    var next = currentIndex < (len - 1) ? currentIndex + 1 : 0;
    showImage(next);
}

//上一张
function ShowPrep(){
    var len = $('#OriginalPic img').length;
    var next = currentIndex == 0 ? (len - 1) : currentIndex - 1;
    showImage(next);
}

//下一张事件
$("#aNext").click(function(){
    ShowNext();
    if($(".active").position().left >= 144 * 5){
        $("#btnNext").click();
    }
});

//上一张事件
$("#aPrev").click(function(){
    ShowPrep();
    if($(".active").position().left <= 144 * 5){
        $("#btnPrev").click();
    }
});

//初始化事件
$(".OriginalPicBorder").ready(function(){
    ShowNext();

    //绑定缩略图点击事件
    $('#ThumbPic li').bind('click',function(e){
        var count = $(this).attr('rel');
        showImage(parseInt(count) - 1);
    });
});




//用户晒图
function DY_scroll(wraper,prev,next,img,speed,or){
    var wraper = $(wraper);
    var prev = $(prev);
    var next = $(next);
    var img = $(img).find('ul');
    var w = img.find('li').outerWidth(true);
    var s = speed;

    var length = 0;
    for(var i=0; i<img.find('li').length; i++){
        length+=img.find('li').eq(i).outerWidth(true);
    }

    img.css('width', length);

    if(img.find('li').length<=3){
        prev.css('display', 'none');
        next.css('display', 'none');
    }

    next.click(function(){
        img.animate({'margin-left':-w},function(){
            img.find('li').eq(0).appendTo(img);
            img.css({'margin-left':0});
        });
    });
    prev.click(function(){
        img.find('li:last').prependTo(img);
        img.css({'margin-left':-w});
        img.animate({'margin-left':0});
    });
    if (or == true){
        ad = setInterval(function() { next.click();},s*1000);
        wraper.hover(function(){
            clearInterval(ad);
        },function(){
            ad = setInterval(function() { next.click();},s*1000);
        });
    }
}