import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-users-management',
  templateUrl: './users-management.component.html',
  styleUrls: ['./users-management.component.css'],
})
export class UsersManagementComponent {
  @Output() openModal = new EventEmitter<string>();
  @Input() users: any[] = [];
}
