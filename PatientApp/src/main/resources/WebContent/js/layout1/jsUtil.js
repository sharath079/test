(function(){
  $('#msbo').on('click', function(){
    $('body').toggleClass('msb-x');
  });
}());


function showModal()
{
    $('[data-name="modalDiv"]').show();
    $('[data-name="modalDiv"]').focus();
}

function closeModal()
{
    $('[data-name="modalDiv"]').hide();
}

function toggleSubmenu(e)
{
    var isCollapsed = $(e).data("isCollapsed");
    if(isCollapsed==1)
    {
	$(e).parent('[data-name="subMenuDiv"]').find('[data-name="subMenuChildrenDiv"]').show();
	$(e).find('[data-name="expandCollapseIcon"]').text('-');
        $(e).data("isCollapsed", 0);
    }	
    else
    {
	$(e).parent('[data-name="subMenuDiv"]').find('[data-name="subMenuChildrenDiv"]').hide();
	$(e).find('[data-name="expandCollapseIcon"]').text('+');
        $(e).data("isCollapsed", 1);
    }	
}


function toggleSidebarMenu()
{
    var isDisplayed = $('[data-name="sidebar-menu"]').data("isDisplayed");
    if(isDisplayed==1)
    {
        $('[data-name="sidebar-menu"]').hide();
        $('[data-name="sidebar-menu"]').data("isDisplayed", 0);
    }
    else
    {
        $('[data-name="sidebar-menu"]').show();        
        $('[data-name="sidebar-menu"]').data("isDisplayed", 1);
    }
}





