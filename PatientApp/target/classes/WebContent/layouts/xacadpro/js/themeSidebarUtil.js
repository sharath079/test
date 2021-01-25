var navbar_dark_skins = [
  'navbar-primary',
  'navbar-secondary',
  'navbar-info',
  'navbar-success',
  'navbar-danger',
  'navbar-indigo',
  'navbar-purple',
  'navbar-pink',
  'navbar-navy',
  'navbar-lightblue',
  'navbar-teal',
  'navbar-cyan',
  'navbar-dark',
  'navbar-gray-dark',
  'navbar-gray',
]

var navbar_light_skins = [
  'navbar-light',
  'navbar-warning',
  'navbar-white',
  'navbar-orange',
]


var sidebar_colors = [
  'bg-primary',
  'bg-warning',
  'bg-info',
  'bg-danger',
  'bg-success',
  'bg-indigo',
  'bg-lightblue',
  'bg-navy',
  'bg-purple',
  'bg-fuchsia',
  'bg-pink',
  'bg-maroon',
  'bg-orange',
  'bg-lime',
  'bg-teal',
  'bg-olive'
]

var accent_colors = [
  'accent-primary',
  'accent-warning',
  'accent-info',
  'accent-danger',
  'accent-success',
  'accent-indigo',
  'accent-lightblue',
  'accent-navy',
  'accent-purple',
  'accent-fuchsia',
  'accent-pink',
  'accent-maroon',
  'accent-orange',
  'accent-lime',
  'accent-teal',
  'accent-olive'
]

var sidebar_skins = [
  'sidebar-dark-primary',
  'sidebar-dark-warning',
  'sidebar-dark-info',
  'sidebar-dark-danger',
  'sidebar-dark-success',
  'sidebar-dark-indigo',
  'sidebar-dark-lightblue',
  'sidebar-dark-navy',
  'sidebar-dark-purple',
  'sidebar-dark-fuchsia',
  'sidebar-dark-pink',
  'sidebar-dark-maroon',
  'sidebar-dark-orange',
  'sidebar-dark-lime',
  'sidebar-dark-teal',
  'sidebar-dark-olive',
  'sidebar-light-primary',
  'sidebar-light-warning',
  'sidebar-light-info',
  'sidebar-light-danger',
  'sidebar-light-success',
  'sidebar-light-indigo',
  'sidebar-light-lightblue',
  'sidebar-light-navy',
  'sidebar-light-purple',
  'sidebar-light-fuchsia',
  'sidebar-light-pink',
  'sidebar-light-maroon',
  'sidebar-light-orange',
  'sidebar-light-lime',
  'sidebar-light-teal',
  'sidebar-light-olive'
]



var navbar_all_colors       = navbar_dark_skins.concat(navbar_light_skins)
var logo_skins = navbar_all_colors

function toggleNavbarBorder()
{
    var hasBorder  = $('.main-header').hasClass('border-bottom-0');
    if(hasBorder==1)
    {
        $('.main-header').removeClass('border-bottom-0');
    }
    else
    {
        $('.main-header').addClass('border-bottom-0');
    }
}

function toggleBodySmallText()
{
    var hasBodySmallText  = $('body').hasClass('text-sm');
    if(hasBodySmallText==1)
    {
        $('body').removeClass('text-sm');
    }
    else
    {
        $('body').addClass('text-sm')
    }
}

function toggleNavbarSmallText()
{
    var hasNavbarSmallText  = $('.main-header').hasClass('text-sm');
    if(hasNavbarSmallText==1)
    {
        $('.main-header').removeClass('text-sm');
    }
    else
    {
        $('.main-header').addClass('text-sm');
    }
}

function toggleSidebarNavSmallText()
{
    var hasSidebarNavSmallText  = $('.nav-sidebar').hasClass('text-sm');
    if(hasSidebarNavSmallText==1)
    {
        $('.nav-sidebar').removeClass('text-sm');
    }
    else
    {
        $('.nav-sidebar').addClass('text-sm');
    }
}

function toggleFooterSmallText()
{
    var hasFooterSmallText  = $('.main-footer').hasClass('text-sm');
    if(hasFooterSmallText==1)
    {
        $('.main-footer').removeClass('text-sm');
    }
    else
    {
        $('.main-footer').addClass('text-sm');
    }
}

function toggleSidebarNavFlatStyle()
{
    var hasNavbarFlatStyle  = $('.nav-sidebar').hasClass('nav-flat');
    if(hasNavbarFlatStyle==1)
    {
        $('.nav-sidebar').removeClass('nav-flat');
    }
    else
    {
        $('.nav-sidebar').addClass('nav-flat');
    }
}

function toggleSidebarNavLegacyStyle()
{
    var hasNavbarLegacyStyle  = $('.nav-sidebar').hasClass('nav-legacy');
    if(hasNavbarLegacyStyle==1)
    {
        $('.nav-sidebar').removeClass('nav-legacy');
    }
    else
    {
        $('.nav-sidebar').addClass('nav-legacy');
    }
}

function toggleSidebarNavCompact()
{
    var hasNavbarCompact  = $('.nav-sidebar').hasClass('nav-compact');
    if(hasNavbarCompact==1)
    {
        $('.nav-sidebar').removeClass('nav-compact');
    }
    else
    {
        $('.nav-sidebar').addClass('nav-compact');
    }
}

function toggleSidebarNavChildIndent()
{
    var hasNavbarChildIndent  = $('.nav-sidebar').hasClass('nav-child-indent');
    if(hasNavbarChildIndent==1)
    {
        $('.nav-sidebar').removeClass('nav-child-indent');
    }
    else
    {
        $('.nav-sidebar').addClass('nav-child-indent');
    }
}

function toggleBrandSmallText()
{
    var hasBrandSmallText  = $('.brand-link').hasClass('text-sm');
    if(hasBrandSmallText==1)
    {
        $('.brand-link').removeClass('text-sm');
    }
    else
    {
        $('.brand-link').addClass('text-sm');
    }
}

function toggleFixedSidebar()
{
    var hasLayoutFixed  = $('body').hasClass('layout-fixed');
    if(hasLayoutFixed==1)
    {
        $('body').removeClass('layout-fixed');
    }
    else
    {
        $('body').addClass('layout-fixed');
    }
}

function toggleFixedNavbar()
{
    var hasLayoutNavbarFixed  = $('body').hasClass('layout-navbar-fixed');
    if(hasLayoutNavbarFixed==1)
    {
        $('body').removeClass('layout-navbar-fixed');
    }
    else
    {
        $('body').addClass('layout-navbar-fixed');
    }
}

function toggleBoxedLayout()
{
    var hasLayoutBoxed  = $('body').hasClass('layout-boxed');
    if(hasLayoutBoxed==1)
    {
        $('body').removeClass('layout-boxed');
    }
    else
    {
        $('body').addClass('layout-boxed');
    }
}

function applyNavbarColor(color)
{
    //var color = $(this).data('color')
    var $main_header = $('.main-header')
    $main_header.removeClass('navbar-dark').removeClass('navbar-light')
    navbar_all_colors.map(function (color) {
      $main_header.removeClass(color)
    })

    if (navbar_dark_skins.indexOf(color) > -1) {
      $main_header.addClass('navbar-dark')
    } else {
      $main_header.addClass('navbar-light')
    }

    $main_header.addClass(color)    
}

function applySidebarNavColor(color)
{
    //var color         = $(this).data('color')
    var accent_class = color
    var $body      = $('body')
    accent_colors.map(function (skin) {
        $body.removeClass(skin)
    })

    $body.addClass(accent_class)
}

function applySidebarDarkVariant(color)
{
    var sidebar_class = 'sidebar-dark-' + color.replace('bg-', '')
    var $sidebar      = $('.main-sidebar')
    sidebar_skins.map(function (skin) {
      $sidebar.removeClass(skin)
    })

    $sidebar.addClass(sidebar_class)
}

function applySidebarLightVariant(color)
{
    var sidebar_class = 'sidebar-light-' + color.replace('bg-', '')
    var $sidebar      = $('.main-sidebar')
    sidebar_skins.map(function (skin) {
      $sidebar.removeClass(skin)
    })

    $sidebar.addClass(sidebar_class)
}

function applyBrandLogoVariant(color)
{
    var $logo = $('.brand-link');
    logo_skins.map(function (skin) {
        $logo.removeClass(skin)
    })
    $logo.addClass(color)
}




function condfigureSidebarElements($container)
{
    var sidebarContentElement = $("[data-name='sidebarContent']");    
    $container.append(sidebarContentElement[0].innerHTML);
}


function initialiseThemeSidebar()
{
    'use strict'

    var $sidebar = $('.control-sidebar')
    var $container = $('<div />', {
        class: 'p-3 control-sidebar-content'
    })

    $sidebar.append($container)

    condfigureSidebarElements($container);
    
}


