import { Component, EventEmitter, HostListener, Output } from '@angular/core';

@Component({
  selector: 'app-delete-user-modal',
  templateUrl: './delete-user-modal.component.html',
  styleUrls: ['./delete-user-modal.component.css'],
})
export class DeleteUserModalComponent {
  @Output() cancel = new EventEmitter<string>();

  // handler per i click fuori dal modal
  // private wasInside = true;

  // @HostListener('document:click')
  // clickout() {
  //   if (!this.wasInside) {
  //     console.log('outside');
  //     this.cancel.emit('');
  //   }

  //   this.wasInside = false;
  // }
}
