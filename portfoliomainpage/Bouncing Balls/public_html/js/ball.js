var Ball = (function(options){
	
var data = {	
	index: 0,
	size: 10,
	color: "green",
	speed : {
		x: 10,
		y: -5
	},
	pos: {
		left: 250,
		top: 70
	},
	container : "body"
	};	
	
	$.extend(data, options);
	
	var div = $("<div/>");
	div.css({
		backgroundColor: data.color,
		left: data.pos.left,
		top: data.pos.top,
		width: data.size,
		height: data.size
	});
	
	$(data.container).append(div);
	
	

	function move (balls){
		data.pos.left += data.speed.x;
		data.pos.top += data.speed.y;
		
		// check of ball is past the left or right edge
		if (data.pos.left <= 0){
			data.speed.x = -data.speed.x;
			data.pos.left += -data.pos.left;
		} else if (data.pos.left >= $(data.container).width() - data.size){
			data.speed.x = -data.speed.x;
			
			var rightEdge = data.pos.left + data.size;
			var howManyPixelsTooFar = $(data.container).width() - rightEdge;
			var finalRight = $(data.container).width() + howManyPixelsTooFar;
			var finalLeft = finalRight - data.size;
			data.pos.left = finalLeft;
			
			
		} 
		
		if (data.pos.top <= 0){
			data.speed.y = -data.speed.y;
			data.pos.top += -data.pos.top;
		} else if (data.pos.top >= $(data.container).height() - data.size){
			data.speed.y = -data.speed.y;
			
			var bottomEdge = data.pos.top + data.size;
			var howManyPixelsTooFar = $(data.container).height() - bottomEdge;
			var finalBottom = $(data.container).height() + howManyPixelsTooFar;
			var finalTop = finalBottom - data.size;
			data.pos.top = finalTop;
			
			
		} 
		div.css({
			left: data.pos.left,
			top: data.pos.top
		});
		
		/* 
		* 1 - get position of current ball
		* 2 - compare that position to all other balls' positions
		* 3 - if collision, remove colliding balls from the array and screen
		* 4 - make sure we do this for all balls
		 */

		for (var i=0;i<balls.length;i++){	
			if (i !== data.index){
				var currentCenterX = data.pos.left + data.size/2;
				var currentCenterY = data.pos.top + data.size/2;
				var comparisonCenterX = balls[i].getPos().left + balls[i].getSize()/2;
				var comparisonCenterY = balls[i].getPos().top + balls[i].getSize()/2;
				//compute difference between the two balls locations
				var deltaX = currentCenterX - comparisonCenterX;
				var deltaY = currentCenterY - comparisonCenterY;
				var distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
				//current ball size = 20, comparison ball = 30
				if (distance < data.size/2 + balls[i].getSize()/2) {
					div.remove();
					balls[i].removeDiv();
					//remove the ball we are moving from the balls array, and the compare ball from balls
					balls.splice(i,1);
					//every index greater then i must have 1 subtracted from it
					
					balls.splice(data.index,1);
					//adjust the stored indexes to account for those removed
					for (var balli = i; balli <balls.length;balli++){
						balls[balli].updateIndex();
					}
					
				}
				else{
					i++;
				}
			}
		}
	};
	
	function getPosition(){
		var copyDataPos = {
			left: data.pos.left,
			top: data.pos.top
		};
		return copyDataPos; 
		
	}
	
	function getSize(){
		var copySize = data.size;
		return copySize;
	}
	
	function removeDiv(){
		div.remove();
	}
	
	function updateIndex(){
		
	}
	return {
		move: move,  // key: function - returns creation of javascript object
		getPos: getPosition,
		getSize: getSize,
		removeDiv: removeDiv,
		updateIndex: updateIndex
	};
});

var ball1 = Ball();
ball1.move();

var ball2 = Ball();
ball2.move();


balls[i].getPos().left
