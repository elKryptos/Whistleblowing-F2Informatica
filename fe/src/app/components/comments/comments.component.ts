import { Component, Input } from '@angular/core';
import { ApiNoIntService } from 'src/app/services/api-no-int.service';
import { UtilService } from 'src/app/services/util.service';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css'],
})
export class CommentsComponent {
  constructor(private api: ApiNoIntService, public util: UtilService) {}

  commentsOpen: boolean = true;
  newComment: any = {};
  @Input() report: any;

  createComment(): void {
    let token = sessionStorage.getItem('token');
    let claims;

    if (token) {
      claims = JSON.parse(window.atob(token.split('.')[1]));
    }

    this.newComment.reportId = this.report.id;
    this.newComment.utente = claims;
    console.log(this.newComment);

    this.api.createComment(this.newComment).subscribe({
      next: (r) => {
        console.log(r);
        this.report.commenti.push(r);
        this.newComment.testo = '';
      },
      error: (e) => {
        console.log(e);
      },
    });
  }
}
