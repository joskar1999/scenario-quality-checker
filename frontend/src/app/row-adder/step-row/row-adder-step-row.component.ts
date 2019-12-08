import {Component, EventEmitter, Input, Output} from "@angular/core";
import {Step} from "../../scenario/model/app-scenario.model";

@Component({
  selector: 'row-adder-step-row',
  template: `
    <div class="row-adder-step-row-container" *ngIf="depth<=2">
      <div class="rows" *ngFor="let element of steps">
        <div class="row">
          <div class="label" [ngStyle]="{'position': 'relative', 'left.px': 10*depth}">
            {{getLabel()}}
          </div>
          <input class="input" [(ngModel)]="element.description" (change)="stepsChange.emit(steps)">
        </div>
        <row-adder-step-row [(steps)]="element.subSteps" [depth]="depth+1"></row-adder-step-row>
      </div>
      <div class="row">
        <div class="label" [ngStyle]="{'position': 'relative', 'left.px': 10*depth}">
          {{getLabel()}}
        </div>
        <div style="display: flex">
          <input class="input" [(ngModel)]="newStep.description">
          <add-button style="margin-left: 10px" (click)="addToList()"></add-button>
        </div>
      </div>
    </div>
  `,
  styleUrls: ['row-adder-step-row.component.scss']
})
export class RowAdderStepRowComponent {
  @Input() depth: number = 0;
  @Input() steps: Step [];
  @Output() stepsChange: EventEmitter<Step []> = new EventEmitter<Step []>();

  newStep: Step = {
    description: '',
    subSteps: []
  };

  addToList(): void {
    if (this.newStep.description.length > 0) {
      this.steps.push(this.newStep);
      this.newStep = {
        description: '',
        subSteps: []
      };
      this.stepsChange.emit(this.steps);
    }
  }

  getLabel(): string {
    if (this.depth === 0) {
      return 'step';
    } else if (this.depth === 1) {
      return 'substep';
    } else {
      return 'sub-substep';
    }
  }
}
