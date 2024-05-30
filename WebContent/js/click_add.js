/**
 * ボタンクリック時の処理を記述
 */

	//登録ボタンの指定
	let button = document.getElementById('click_add');
	//ボタンを押すと渓谷
	//登録ボタン
	button.onclick = function(){
		//確認ウィンドウを表示
		if(window.confirm('追加しますか？')){
			return true;

		}else{
			return false;
		}
	};