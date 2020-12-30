const { interval } = rxjs;
const container = document.querySelector('.container');
interval(1000)
  .subscribe(x => container.innerHTML = x);
