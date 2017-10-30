function rulePage(obj){
    var navs = obj.parentNode.getElementsByTagName("li");
    navs[1].setAttribute("class", "");
    navs[2].setAttribute("class", "active");
    var vote = obj.parentNode.parentNode.getElementsByClassName("vote-list");
    vote[0].style.display="none";
    var rule = obj.parentNode.parentNode.getElementsByClassName("vote-rules");
    rule[0].style.display="block";
}

function votePage(obj){
    var navs = obj.parentNode.getElementsByTagName("li");
    navs[2].setAttribute("class", "");
    navs[1].setAttribute("class", "active");
    var rule = obj.parentNode.parentNode.getElementsByClassName("vote-rules");
    rule[0].style.display="none";
    var vote = obj.parentNode.parentNode.getElementsByClassName("vote-list");
    vote[0].style.display="block";
}

function clickLike(obj){
    var imgs = obj.getElementsByTagName("img");
    if (imgs[1].style.display=="none") {
        alert("今天已经为该品牌投过票了，请明天再尝试。");
        return;
    } else {
        var eles = obj.parentNode.getElementsByTagName("div");
        var name = eles[0].innerText;
        // js 获得html内容和ip地址，放入data
        console.log(name);
        $.ajax({
            type: 'POST',
            url: 'vote',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({'name':name}),
            success: function(data) {
            	if (data.state == "failure") {
            		alert(data.msg);
            	} else {
            		imgs[0].style.display="inherit";
                    imgs[1].style.display="none";
            		var votes = parseInt(eles[2].innerText);
                    eles[2].innerText = votes + 1;
            	}
            },
            dataType: 'json'
        });
    }
}

function loadPage() {
	$.ajax({
        type: 'GET',
        url: 'vote',
        success: function(data) {
        	appendVoteList(data);
        },
        dataType: 'json'
    });
}

function appendVoteList(data) {
	var types = new Set();
	for (var row of data) {
		types.add(row.type);
	}
	for (var ele of types) {
        var list = '';
		list += '<div class="list"><div class="list-banner"><div class="list-banner-avatar"></div><div class="list-banner-context">'+ele+'</div></div><div class="list-context">';
        for (var row of data) {
            console.log(row.votes);
            var str = '';
            if (row.type == ele) {
                str += '<div class="list-context-element">' + '<div class="list-context-element-img"><img src="' + row.logo + '"></div>' + '<div class="list-context-element-namerow">' + '<div class="list-context-element-namerow-name">'+row.name+'</div>';
                if (row.voted)
                    str += '<div onClick="clickLike(this)" class="list-context-element-namerow-like"><img src="img/like_red.png"><img src="img/like_white.png" style="display: none;"></div>';
                else
                    str += '<div onClick="clickLike(this)" class="list-context-element-namerow-like"><img src="img/like_red.png" style="display: none;"><img src="img/like_white.png"></div>';
                str += '<div class="list-context-element-namerow-number">'+row.votes+'</div></div></div>';
            }
            list += str;
        }
        list += '</div></div>';
		$(".vote-list").append(list);
	}
}