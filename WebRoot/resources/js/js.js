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

    preFlex();

    reviewsType();

    AddLess();

    order();

    tags();

    backType();

    $('#slider').easySlider({
        auto: false,
        continuous: true,
    });
 
})

// 下拉效果
function nav_Drop() {

    $('#login').on('mouseenter', function() {
        $(this).css('background', 'url(Images/topListActive.jpg)');
        $(this).children('.login_sednav').stop().slideDown(300);
    })

    $('#login').on('mouseleave', function() {
        $(this).css('background', '');
        $(this).children('.login_sednav').stop().slideUp(300);
    }) 

    $('#cart').on('mouseenter', function() {
        $(this).children('.cart').css('background', 'url(Images/topListActive.jpg)');
        $(this).children('.cart_sednav').stop().slideDown(300);
    })

    $('#cart').on('mouseleave', function() {
        $(this).children('.cart').css('background', '');
        $(this).children('.cart_sednav').stop().slideUp(300);
    })
}

// 三级导航
// function three_Nav() {

//     var th_Nav = $('#sed_nav_item').find('.three_nav');
//     var th_Div = $('#sed_nav_item').find('.three_nav_item');

//     $('#all_shop').bind('mouseover', function() {
//         $(this).children('.all_shop_sednav').stop().slideDown(300);
//     })    

//     th_Nav.each(function() {
//         $(this).bind('mouseenter', function(){
//             $(this).find('.three_nav_item').css('display', 'block');     
//         }) 

//         $(this).bind('mouseleave', function(){
//             $(this).find('.three_nav_item').css('display', 'none');    
//             $('#all_shop').children('.all_shop_sednav').stop().slideUp(300);
//         })
//     })

//     $('#all_shop').bind('mouseleave', function() {
//         $(this).children('.all_shop_sednav').stop().slideUp(300);
//     })
// }    

// 三级导航
function three_Nav() {
    var startItem = $('#drop_item').find('.drop_item');
    var sedItem = startItem.find('.sed_navItem');
    var thrItem = sedItem.find('li');
    
    startItem.on('mouseenter', function() {
        sedItem.stop().slideDown(300);
    })

    startItem.on('mouseleave', function() {
        sedItem.stop().slideUp(300);
    })

        // sedItem.on('mouseleave', function() {
        //     $(this).stop().slideUp(300);
        // })

        // thrItem.each(function() {
        //     $(this).on('mouseenter', function() {
        //         $(this).find('.thr_navItem').css('display', 'block');
        //     })

        //     $(this).on('mouseleave', function() {
        //         $(this).find('.thr_navItem').css('display', 'none');
        //     })
        // })
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
    // var chose_loge = $('#chose_loge');
    // var chose_sign = $('#chose_sign');

    // if(chose_loge.is(':checked')){
    //     $('.sign_in').hide();
    //     $('.login_in').show();
    // }else if(chose_sign.is(':checked')){
    //     $('.login_in').hide();
    //     $('.sign_in').show();
    // }

    // chose_loge.change(function() {
    //     $('.sign_in').hide();
    //     $('.login_in').show();
    // })

    // chose_sign.change(function() {
    //     $('.login_in').hide();
    //     $('.sign_in').show();
    // })


    var login = $('#login_form');
    var login_ipt = login.find('input[type="text"]');

    var sign = $('#sign_form');
    var sign_ipt = sign.find('.sign_must');

    var isRight = true;
    var email_check = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.(?:com|cn)$/;

    // login.submit(function(event) {
    //     //isRight = true;
    //     //event.preventDefault();
    //     // for(var i=0;i<login_ipt.length; i++){

    //     //     login_ipt.eq(i).next('span').text('');

    //     //     if(login_ipt.eq(i).val() == ''){
    //     //         login_ipt.eq(i).next('span').text('不能为空');
    //     //         isRight = false;
    //     //     }
    //     // }
    //     // if(isRight == false){
    //     //     return false;
    //     // }
    //     login.find('span').text('');
        
    //     if(login.find('#lemail').val()=='' && login.find('#lpassword').val()==''){
    //         login.find('#lemail').next('span').text('请输入注册邮箱');
    //         login.find('#lpassword').next('span').text('请输入密码');
    //         return false;
    //     }
    //     if(login.find('#lemail').val()==''){
    //         login.find('#lemail').next('span').text('请输入注册邮箱');
    //         return false
    //     }
    //     if(login.find('#lpassword').val()=='' && !login.find('#lemail').val()=='' && email_check.test(login.find('#lemail').val())){
    //         login.find('#lpassword').next('span').text('请输入密码');
    //         return false
    //     }
    //     if(!login.find('#lemail').val()=='' && !email_check.test(login.find('#lemail').val()) && !login.find('#lpassword').val()==''){
    //         login.find('#lemail').next('span').text('邮箱格式错误');
    //         return false;
    //     }
    //     if(!login.find('#lemail').val()=='' && login.find('#lpassword').val()=='' && !email_check.test(login.find('#lemail').val())){
    //         login.find('#lemail').next('span').text('邮箱格式错误');
    //         login.find('#lpassword').next('span').text('请输入密码');
    //         return false;
    //     }
    // });

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
        
        //判断电话号码
        if($('#telephone').val()!='' && !phone.test($('#phone').val())){
            $('#telephone').next('span').text('手机格式错误');
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

// 数量框中禁止输入数字以外的东西
function onlyNum(e) {
    var k = window.event ? e.keyCode : e.which;
    if(((k >= 48) && (k <= 57)) || k == 8 || k == 0){
    }else{
        if (window.event) {
            window.event.returnValue = false;
        }
        else {
            e.preventDefault(); //for firefox 
        }
    }
}

// 个人中心收缩效果
function preFlex() {
    var flex = $('.pre_flexBox p');
    flex.each(function() {
        $(this).on('click', function() {
            if($(this).next('.pre_flexContent').is(':visible')){
                $(this).children('span').css('background-position', '0 0');
                $(this).next('.pre_flexContent').slideUp(300);
            }
            else{
                $(this).children('span').css('background-position', '0 -19px');
                $(this).next('.pre_flexContent').slideDown(300);
            }
        })
    })
}

// 评论类型切换
function reviewsType() {
    var reviewsType = $('#reviews_chose ul li');
    var reviews_list = $('.reviews_list');
    reviewsType.each(function(index) {
        $(this).on('click', function() {
            if($(this).hasClass('active')){
                return ;
            }else{
                reviewsType.removeClass('active');
                $(this).addClass('active');
                if(index == 0){
                    reviews_list.hide();
                    $('#reviews_alllist').show();
                }     
                if(index == 1){
                    reviews_list.hide();
                    $('#reviews_goodlist').show();
                }  
                if(index == 2){
                    reviews_list.hide();
                    $('#reviews_normallist').show();
                } 
                if(index == 3){
                    reviews_list.hide();
                    $('#reviews_badlist').show();
                } 
            }
        })
    })
}

// 详情页数量加减
function AddLess() {
    getVal = $('.buy_count input').val();
    $('.buy_add').on('click', function() {
        $('.buy_count input').attr('value', ++getVal);
    })

    $('.buy_less').on('click', function() {
        $('.buy_count input').attr('value', --getVal);
    })
}

// 订单切换
function order() {
    var ordertype = $('#order_type span');
    ordertype.each(function(index) {

        $(this).on('click', function() {
            if($(this).hasClass('active')){
                return ;
            }else{
                ordertype.removeClass('active');
                $(this).addClass('active');
                
                if(index==0){
                    $('.unfinished_orders').css('display', 'block');
                    $('.finish_orders').css('display', 'none');
                }else if(index==1){
                    $('.finish_orders').show();
                    $('.unfinished_orders').css('display', 'none');
                }
            }
        })
        
    })
}

// tag标签
function tags() {
    var goodtags = $('#good_tags span');
    var goodinput = $('#good_tags input[type="text"]');
    var goodadd = $('#good_tags input[type="button"]');
    var goodtarget = $('#good_tags .good_tags');

    var badtags = $('#bad_tags span');
    var badinput = $('#bad_tags input[type="text"]');
    var badadd = $('#bad_tags input[type="button"]');
    var badtarget = $('#bad_tags .bad_tags');

    goodtags.each(function() {
        $(this).on('click', function() {
            if(!$(this).hasClass('goodtag_choose')){
                $(this).addClass('goodtag_choose');
            }else{
                $(this).removeClass('goodtag_choose');
            }
        })
    })

    goodadd.on('click', function() {
        if(goodinput.val() != ''){
            var _html = '<span class="goodtag_choose add_goodtag">' + goodinput.val() + '</span>';
            goodtarget.append(_html);
        }

        for(var i=0; i<$('#good_tags .add_goodtag').length; i++){
            $(document).on('click', '.add_goodtag', function() {
                $(this).stop().fadeOut(500,function() {
                    $(this).remove();
                });  
            }) 
        }       
    })   
    
    badtags.each(function() {
        $(this).on('click', function() {
            if(!$(this).hasClass('badtag_choose')){
                $(this).addClass('badtag_choose');
            }else{
                $(this).removeClass('badtag_choose');
            }
        })
    })

    badadd.on('click', function() {
        if(badinput.val() != ''){
            var _html = '<span class="badtag_choose add_badtag">' + badinput.val() + '</span>';
            badtarget.append(_html).fadeIn(300);
        }

        for(var i=0; i<$('#bad_tags .add_badtag').length; i++){
            $(document).on('click', '.add_badtag', function() {
                $(this).stop().fadeOut(500,function() {
                    $(this).remove();
                });  
            }) 
        }       
    })  
}

// 回购方式
function backType() {
    var getOlder = $('#get_older');
    var getMoney = $('#get_money');
    var nextType = $('#next_type');
    var getDiscount = $('#discount');
    var getNum = $('#proNum');

    $('#proNum_error').text = '';

    nextType.on('click', function() {
        if(getNum.val() == ''){
            $('#proNum_error').css({
                display: 'block',
                marginTop: '20px',
                color: 'red',
            });

            $('#proNum_error').text('商品编号不能为空');

            return false;
        } 

        if(getOlder.is(':checked')){
            window.location = 'by-getolder.html';
        }else if(getMoney.is(':checked')){
            window.location = 'by-getmoney.html';
        } 
    })

    getDiscount.on('click', function() {
        if(getNum.val() == ''){
            $('#proNum_error').css({
                display: 'block',
                marginTop: '20px',
                color: 'red',
            });

            $('#proNum_error').text('商品编号不能为空');

            return false;
        }else{
            $('.discount_box').stop().slideDown(200);
        }   
    })

    $('#get_olderpage').on('click', function() {
        address_check();
    })
    

    $('#get_moneypage').on('click', function() {
        presonl_check();
    })

    $('#application').on('click', function() {
        otherway_check();
    })
}

// 地址验证
function address_check() {
    $('.address_error').text('');
    var checkItem = $('.address_check');
    var inputCheck = $('.address_check input')
    var isRight = true;

    var phone = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
    var email = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.(?:com|cn)$/;
    var zipCode = /\d{6}/;

    for(var i=0; i<inputCheck.length; i++){
        if(inputCheck.eq(i).val() == ''){
            isRight = false;
            inputCheck.eq(i).next('.address_error').text(inputCheck.eq(i).prev('label').text().replace('：*','') + '不能为空');
        }        
    }

    if($('#s_province').val() == '省份' || $('#s_city').val() == '地级市'){
        $('#s_province').nextAll('.address_error').text('省份不能为空');
        isRight = false;
    }

    if($('#phone').val()!='' && !phone.test($('#phone').val())){
        $('#phone').next('.address_error').text('手机格式从错误');
        isRight = false;
    }

    if($('#email').val()!='' && !email.test($('#email').val())){
        $('#email').next('.address_error').text('邮箱格式错误');
        isRight = false;
    }

    if($('#email').val()!='' && !email.test($('#email').val())){
        $('#email').next('.address_error').text('邮箱格式错误');
        isRight = false;
    }

    if($('#zip-code').val()!='' && !zipCode.test($('#zip-code').val())){
        $('#zip-code').next('.address_error').text('邮编格式错误');
        isRight = false;
    }

    if(isRight == false){
        return false;
    }else{
        window.location = 'buy-back-complete.html';
    }
}

// 折现个人信息验证
function presonl_check() {
    $('.address_error').text('');
    var presonal = $('.presonal_check');
    var inputCheck = presonal.find('input');

    var phone = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
    var bank = /^(\d{4})\d+(\d{4})$/;

    var isRight = true;

    for(var i=0; i<inputCheck.length; i++){
        if(inputCheck.eq(i).val() == ''){
            isRight = false;
            inputCheck.eq(i).next('.address_error').text(inputCheck.eq(i).prev('label').text().replace('：*','') + '不能为空');
        }
    }

    if($('#bank-card').val()!='' && !bank.test($('#bank-card').val())){
        $('#bank-card').next('.address_error').text('银行卡格式错误');
        isRight = false;
    }

    if($('#phone').val()!='' && !phone.test($('#phone').val())){
        $('#phone').next('.address_error').text('手机格式从错误');
        isRight = false;
    }

    if(isRight == false){
        return false;
    }else{
        window.location = 'buy-back-complete.html';
    }
}

// 非本站商品回购申请验证
function otherway_check() {
    $('.address_error').text('');
    var checkItem = $('.other_way').find('input[type="text"]');
    var isRight = true;

    var phone = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
    var email = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.(?:com|cn)$/;

    for(var i=0; i<checkItem.length; i++){
        if(checkItem.eq(i).val() == ''){
            isRight = false;
            checkItem.eq(i).next('.address_error').text(checkItem.eq(i).prev('label').text().replace('：*','') + '不能为空');
        }        
    }

    if($('#otherway-infor').val() == ''){
        $('#otherway-infor').next('.address_error').text($('#otherway-infor').prev('label').text().replace('：*','') + '不能为空');
    }

    if($('#otherway-phone').val() != '' && !phone.test($('#otherway-phone').val())){
        $('#otherway-phone').next('.address_error').text('电话格式错误');
    }

    if($('#otherway-email').val() != '' && !email.test($('#otherway-email').val())){
        $('#otherway-email').next('.address_error').text('邮箱格式错误');
    }

    if(isRight == false){
        return false;
    }else{
        window.location = 'buy-back-complete.html';
    }
}
