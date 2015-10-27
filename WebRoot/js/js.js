$(function() {
    nav_Drop();

    three_Nav();

    check_Emial();

    detailed('pro_imgbox','detailed_message');

    detailed('pro_imgbox','detailed_message');

    share();

    ratings();

    leftAccordion();

    inputCheck();

    $('#slider').easySlider({
        auto: false,
        continuous: true,
    });
 
})

// 下拉效果
function nav_Drop() {

    $('#login').bind('mouseover', function() {
        $(this).css('background', '#44b9d9');
        $(this).children('.login_sednav').stop().slideDown(300);
    })

    $('#login').bind('mouseout', function() {
        $(this).css('background', '');
        $(this).children('.login_sednav').stop().slideUp(300);
    }) 

    $('#cart').bind('mouseover', function() {
        $(this).children('.cart').css('background', '#44b9d9');
        $(this).children('.cart_sednav').stop().slideDown(300);
    })

    $('#cart').bind('mouseout', function() {
        $(this).children('.cart').css('background', '');
        $(this).children('.cart_sednav').stop().slideUp(300);
    })
}

// 三级导航
function three_Nav() {

    var th_Nav = $('#sed_nav_item').find('.three_nav');
    var th_Div = $('#sed_nav_item').find('.three_nav_item');

    for(var i=0; i<th_Div.length; i++){
        th_Div.eq(i).css('top', -i*24 - 10 + 'px');
    }

    $('#all_shop').bind('mouseover', function() {
        $(this).children('.all_shop_sednav').stop().slideDown(300);
    })    

    th_Nav.each(function() {
        $(this).bind('mouseenter', function(){
            $(this).find('.three_nav_item').stop().show();    
        }) 

        $(this).bind('mouseleave', function(){
            $(this).find('.three_nav_item').stop().hide();    
            $('#all_shop').children('.all_shop_sednav').stop().slideUp(300);
        })
    })

    $('#all_shop').bind('mouseleave', function() {
        $(this).children('.all_shop_sednav').stop().slideUp(300);
    })
}    

// 分享图标定位
function share() {
    var icon = $('#share_link').find('a');
    for(var i=0; i<icon.length; i++) {
        icon.eq(i).css('background-position', -i*35 + 'px 0');
    }

    var friend = $('#friend').find('a');
    for(var i=0; i<friend.length; i++){
        if(i==0){
            friend.eq(i).css('background-position', '-3px 0');    
        }else{
            friend.eq(i).css('background-position', -i*88 -3 + 'px 0');
        }      
    }
}

// 首页E-mail验证
function check_Emial() {
    var email =  /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.(?:com|cn)$/;

    var getval = 0;

    $('#check_email').on('blur', function(){
        getval = $(this).val();
        if(getval == ''){
            $('.error_message').text('邮箱不能为空'); 
            $('#sub_email').css('background','#ccc'); 
            $('#sub_email').attr('disabled', 'true');  
        }else if(!email.test(getval)){
            $('.error_message').text('请输入正确的邮箱地址'); 
            $('#sub_email').css('background','#ccc'); 
            $('#sub_email').attr('disabled', 'true');
        }else{
            $('.error_message').text('');
            $('#sub_email').css('background','#0485c6'); 
            $('#sub_email').removeAttr('disabled');
        }
    })  
}

// 商品移入详情
function detailed(imgbox, showbox) {
    var img_box = $('.'+imgbox);
    img_box.each(function() {
        $(this).on('mouseenter', function(){
            $(this).find('.'+showbox).css('display', 'block');
        })
        $(this).on('mouseleave', function(){
            $(this).find('.'+showbox).css('display', 'none');
        })
    })    
}

// 左侧搜索栏按评分图标定位
function ratings() {
    var rating = $('.sort_rating').find('label');
    for(var i=0; i<rating.length; i++){
        rating.eq(i).css('background-position', '0 ' + -(200-i*40) + 'px');
    }
}

// 左侧搜索栏收放
function leftAccordion() {
    var accordion = $('.accordion');
    accordion.each(function() {
        $(this).on('click', function() {
            if(!$(this).children('p').hasClass('acd_hide')){
                $(this).children('p').addClass('acd_hide');
                $(this).next('.accordion_content').hide();
            }else{
                $(this).children('p').removeClass('acd_hide');
                $(this).next('.accordion_content').show();
            }
        })
    })
}

// 登录注册验证及选择判断
function inputCheck() {
    var chose_loge = $('#chose_loge');
    var chose_sign = $('#chose_sign');

    if(chose_loge.is(':checked')){
        $('.sign_in').hide();
        $('.login_in').show();
    }else if(chose_sign.is(':checked')){
        $('.login_in').hide();
        $('.sign_in').show();
    }

    chose_loge.change(function() {
        $('.sign_in').hide();
        $('.login_in').show();
    })

    chose_sign.change(function() {
        $('.login_in').hide();
        $('.sign_in').show();
    })


    var login = $('#login_form');
    var login_ipt = login.find('input[type="text"]');

    var sign = $('#sign_form');
    var sign_ipt = sign.find('.sign_must');

    var isRight = true;
    var email_check = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.(?:com|cn)$/;

    login.submit(function(event) {
        //isRight = true;
        //event.preventDefault();
        // for(var i=0;i<login_ipt.length; i++){

        //     login_ipt.eq(i).next('span').text('');

        //     if(login_ipt.eq(i).val() == ''){
        //         login_ipt.eq(i).next('span').text('不能为空');
        //         isRight = false;
        //     }
        // }
        // if(isRight == false){
        //     return false;
        // }
        login.find('span').text('');
        
        if(login.find('#lemail').val()=='' && login.find('#lpassword').val()==''){
            login.find('#lemail').next('span').text('请输入注册邮箱');
            login.find('#lpassword').next('span').text('请输入密码');
            return false;
        }
        if(login.find('#lemail').val()==''){
            login.find('#lemail').next('span').text('请输入注册邮箱');
            return false
        }
        if(login.find('#lpassword').val()=='' && !login.find('#lemail').val()=='' && email_check.test(login.find('#lemail').val())){
            login.find('#lpassword').next('span').text('请输入密码');
            return false
        }
        if(!login.find('#lemail').val()=='' && !email_check.test(login.find('#lemail').val()) && !login.find('#lpassword').val()==''){
//            login.find('#lemail').next('span').text('邮箱格式错误');
//            return false;
        }
        if(!login.find('#lemail').val()=='' && login.find('#lpassword').val()=='' && !email_check.test(login.find('#lemail').val())){
//            login.find('#lemail').next('span').text('邮箱格式错误');
//            login.find('#lpassword').next('span').text('请输入密码');
//            return false;
        }
    });

    sign.submit(function() {
        sign.find('span').text('');

        isRight = true;

        //event.preventDefault();
        for(var i=0;i<sign_ipt.length; i++){

            sign_ipt.eq(i).next('span').text('');

            if(sign_ipt.eq(i).val() == ''){
                sign_ipt.eq(i).next('span').text(sign.find('label').eq(i).text().replace('：','') + '不能为空');
                isRight = false;
            }
        }

        // 判断用户名长度
        if(!$('#suer_name').val() == '' && ($('#suer_name').val().length>12 || $('#suer_name').val().length<4)){
            $('#suer_name').next('span').text('用户名必须在4-12个字节之间');
            isRight = false;
        }

        //判断密码长度
        if(!$('#spassword').val() == '' && ($('#spassword').val().length>16 || $('#spassword').val().length<6)){
            $('#spassword').next('span').text('密码必须在6-16位之间');
            isRight = false;
        }

        //判断确认密码是否一致
        if(!$('#surepassword').val() == '' && $('#surepassword').val() != $('#spassword').val()){
            $('#surepassword').next('span').text('与密码不一致');
            isRight = false;
        }

        //判断邮箱
        if(!$('#semail').val() == '' && !email_check.test($('#semail').val())){
            $('#semail').next('span').text('邮箱格式不正确');
            isRight = false;
        }

        if(isRight == false){
            return false;
        }
    });
}