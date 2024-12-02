/* global bootstrap: false */
(function () {
  'use strict'
  var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
  tooltipTriggerList.forEach(function (tooltipTriggerEl) {
    new bootstrap.Tooltip(tooltipTriggerEl)
  })
})()

function updateTaskStatus(checkbox) {
        const taskId = checkbox.getAttribute('data-task-id');
        const isCompleted = checkbox.checked;

        fetch(`/update-task-status/${taskId}?completed=${isCompleted}`, {
                method: 'POST'
        }).then(response => {
            if (response.ok) {
                console.log('Task status updated successfully!');
                window.location.href = '/';
            }
        });
    }
