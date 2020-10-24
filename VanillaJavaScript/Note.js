class Note {
  constructor(text, color) {
    this.text = text;
    this.color = color;
  }
}

class UI {
  addNote(note) {
    const noteList = document.getElementById("note-list");
    const element = document.createElement("div");
    element.innerHTML = ` 
             
             <div class="card text-center mb-4 ${note.color}">
                <div class="card-body">
                   <strong>Note Text: </strong>${note.text}
                   <a href="#" class="btn btn-danger" name="delete">Delete Note</a>
                </div>
             </div>

    `;

    noteList.appendChild(element);
  }

  resetForm() {
    document.getElementById("note-form").reset();
  }
  deleteNote(element) {
    if (element.name === "delete") {
      element.parentElement.parentElement.parentElement.remove();
      this.showMessage ("Note deleted.", "info");
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
  .getElementById("note-form")
  .addEventListener("submit", function(e) {

    e.preventDefault();
    const noteText = document.getElementById("note-content").value;
    const noteColor = document.getElementById("note-color").value;

    const newNote = new Note(noteText, noteColor);

    const ui = new UI();
    if (noteText === "" || noteColor === "") {
      return ui.showMessage("Fill note text and color.", "danger");
    }
    ui.addNote(newNote);
    ui.resetForm();
    ui.showMessage("Note added success.", "success");

  });

document
  .getElementById("note-list")
  .addEventListener("click", function(e) {
    const ui = new UI();
    ui.deleteNote(e.target);
  });
