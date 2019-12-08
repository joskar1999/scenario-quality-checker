import {Component, EventEmitter, Input, Output} from "@angular/core";

@Component({
  selector: 'row-adder-string-row',
  template: `
    <div class="row" *ngFor="let element of list">
      <div class="label">
        <p>{{label}}</p>
      </div>
      <input class="input" [(ngModel)]="element" (change)="listChange.emit(list)">
    </div>
    <div class="row">
      <div class="label">
        <p>{{label}}</p>
      </div>
      <div style="display: flex">
        <input class="input" [(ngModel)]="newElement">
        <add-button style="margin-left: 10px" (click)="addToList()"></add-button>
      </div>
    </div>
  `,
  styleUrls: ['row-adder-string-row.component.scss']
})
export class RowAdderStringRowComponent {
  @Input() label: string;
  @Input() list: string [];
  @Output() listChange: EventEmitter<string[]> = new EventEmitter<string[]>();

  newElement: string;

  addToList(): void {
    if (this.newElement.length > 0) {
      this.list.push(this.newElement);
      this.newElement = '';
      this.listChange.emit(this.list);
    }
  }
}
