import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css'],
})
export class SidebarComponent {
  constructor(private router: Router) {}

  @Output() e = new EventEmitter<string>();
  @Input() items: any[] = [];
  @Input() tabSelected: string = '';

  footerItems: any[] = [
    { label: 'Impostazioni', icon: 'lucideSettings', color: 'white' },
  ];

  onSelect(i: string) {
    this.tabSelected = i;
    this.e.emit(i);
  }

  signOut(): void {
    sessionStorage.clear();
    this.router.navigateByUrl('/');
  }
}
