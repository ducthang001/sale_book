category_carousel_right = document.querySelector(".category_carousel_right"); // slider
carousel_images = document.querySelector(".carousel_images"); // slideList
images = carousel_images.querySelectorAll("img");
pre = document.querySelector(".pre_bg-main");
next = document.querySelector(".next_bg-main");
len = images.length;
index = 0;
sliderWidth = category_carousel_right.offsetWidth;
window.addEventListener("resize", () => {
  sliderWidth = category_carousel_right.offsetWidth;
});

var preSlide = function (){
  if (index > 1) {
    index -= 2;
    carousel_images.style.left = "-" + index * sliderWidth + "px";
    index ++;
  }else if(index == 1){
      index -= 1;
      carousel_images.style.left = "-" + index * sliderWidth + "px";
      index++;
  }
};

var nextSlide = function (){
  if (index < len) {
    carousel_images.style.left = "-" + index * sliderWidth + "px";
    index++;
  } else if (index == len) {
    carousel_images.style.left = "0px";
    index = 1;
  }
};

next.addEventListener('click', nextSlide)
pre.addEventListener('click', preSlide)

setInterval(nextSlide, 4000)


