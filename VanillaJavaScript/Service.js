class Service {
  constructor(name, price, year) {
    this.name = name;
    this.price = price;
    this.year = year;
  }
}

class UI {
  addService(service) {
    const servicesList = document.getElementById("services-list");
    const element = document.createElement("div");
    element.innerHTML = ` 
             
             <div class="card text-center mb-4">
                <div class="card-body">
                   <strong>Service: </strong>${service.name}
                   <strong>Price: </strong>${service.price}
                   <strong>Year: </strong>${service.year}
                   <a href="#" class="btn btn-danger" name="delete">Delete Service</a>
                </div>
             </div>

    `;

    servicesList.appendChild(element);
  }

  resetForm() {
    document.getElementById("service-form").reset();
  }
  deleteService(element) {
    if (element.name === "delete") {
      element.parentElement.parentElement.parentElement.remove();
      this.showMessage("Service deleted.", "info");
    }
  }

  showMessage(message, cssClassName) {
    const div = document.createElement("div");
    div.className = `alert alert-${cssClassName} mt-2`;
    div.appendChild(document.createTextNode(message));

    // Show message
    const container = document.querySelector(".container");
    const app = document.querySelector("#App");
    container.insertBefore(div, app);

    setTimeout(function() {
      //Remove all the classes that start with alert
      document.querySelector(".alert").remove();
    }, 2000);
  }
}

// DOM Events
document
  .getElementById("service-form")
  .addEventListener("submit", function(e) {
    const serviceName = document.getElementById("service-name").value;
    const servicePrice = document.getElementById("service-price").value;
    const serviceYear = document.getElementById("service-year").value;

    const newService = new Service(serviceName, servicePrice, serviceYear);

    const ui = new UI();
    e.preventDefault();
    if (serviceName === "" || servicePrice === "" || serviceYear === "") {
      return ui.showMessage("Fill data service.", "danger"); 
    }
    ui.addService(newService);
    ui.resetForm();
    ui.showMessage("Service added success.", "success");

  });

document
  .getElementById("services-list")
  .addEventListener("click", function(e) {
    const ui = new UI();
    ui.deleteService(e.target);
  });
