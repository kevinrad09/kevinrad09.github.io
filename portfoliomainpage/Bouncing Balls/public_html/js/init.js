/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
				var balls = [];
				for (var i=0;i<10;i++){
					var ball1 = Ball({
							size: 50,
							color: "red",
						pos: {
							left: Math.random()*($("body").width()-30),
							top: Math.random()*($("body").height()-30)
						},
						speed: {
							x: Math.random()*20-10,
							y: Math.random()*20-10
						},
						index: 1
					});
					
					balls.push(ball1);
				}
			
			
			
			setInterval(function(){
				for (var i=0;i<balls.length;i++){
				balls[i].move(balls); // the keyword move
				}
			},1000/30);
		});

