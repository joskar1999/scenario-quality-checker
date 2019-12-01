import {Component, OnInit} from '@angular/core';
import {Scenario, Step} from "./scenario/model/app-scenario.model";
import {ScenarioService} from "./scenario/scenario.service";

@Component({
  selector: 'app-root',
  template: `
    <div class="form-container">
      <div class="choose-scenario-buttons-container">
        <div class="button" (click)="showCreateScenario()">Stwórz własny scenariusz</div>
        <div class="button" (click)="showUploadScenario()">Wgraj gotowy scenariusz</div>
      </div>
      <div class="create-scenario" *ngIf="createScenarioActive">
        <div class="row">
          <div class="label">
            <p>Tytuł</p>
          </div>
          <input class="input" [(ngModel)]="scenario.title">
          <add-button></add-button>
        </div>
      </div>
      <div class="upload-scenario" *ngIf="uploadScenarioActive">
        <textarea type="text" class="upload-input" [(ngModel)]="uploadedScenarioInput"></textarea>
      </div>
      <div class="submit-container">
        <div class="submit-buttons-container">

        </div>
        <div class="button" (click)="submit()">Wyślij</div>
        <p>{{submitText}}</p>
      </div>
    </div>
    <div *ngIf="numberOfSteps">Liczba kroków wynosi {{numberOfSteps}}</div>
    <div *ngIf="keywords">Liczba słów kluczowych wynosi {{keywords}}</div>
    <div *ngIf="stepsWithoutActors" class="steps-without-actors">
      Kroki bez aktora:
      <div class="step-without-actor" *ngFor="let step of stepsWithoutActors">
        {{step}}
      </div>
    </div>
  `,
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  scenario: Scenario;
  createScenarioActive: boolean = false;
  uploadScenarioActive: boolean = false;
  uploadedScenarioInput: string;
  submitText: string = '';
  numberOfSteps: number;
  keywords: number;
  stepsWithoutActors: string [];
  documentationSteps: string [];
  flattenScenarioSteps: Step [];

  constructor(private scenarioService: ScenarioService) {
  }

  ngOnInit(): void {
    this.scenario = {
      title: '',
      actors: [],
      systemActors: [],
      steps: []
    }
  }

  resetForm(): void {
    this.scenario.title = '';
    this.scenario.actors = [];
    this.scenario.systemActors = [];
    this.scenario.steps = [];
    this.submitText = '';
  }

  isJson(text: string): boolean {
    try {
      JSON.parse(text);
    } catch (e) {
      return false;
    }
    return true;
  }

  isJsonScenarioObject(text: string): boolean {
    if (this.isJson(text)) {
      this.scenario = JSON.parse(text);
      return <Scenario>this.scenario !== undefined;
    } else {
      return false;
    }
  }

  showUploadScenario(): void {
    this.uploadScenarioActive = true;
    this.createScenarioActive = false;
    this.resetForm();
  }

  showCreateScenario(): void {
    this.uploadScenarioActive = false;
    this.createScenarioActive = true;
    this.resetForm();
  }

  submit(): void {
    if(this.isJsonScenarioObject(this.uploadedScenarioInput)){
      this.submitText = 'Wysłano scenariusz';

      this.scenarioService.getSteps(this.scenario).subscribe(steps => {
        this.numberOfSteps = steps.steps;
      });

      this.scenarioService.getKeywordsAmount(this.scenario).subscribe( keywords => {
        this.keywords = keywords.keywords;
      });

      this.scenarioService.getStepsWithoutActor(this.scenario).subscribe(steps => {
        this.stepsWithoutActors = steps.stepsWithoutActors;
      });

      this.scenarioService.getPrettyPrintedScenario(this.scenario).subscribe(docuentation => {
        this.documentationSteps = docuentation.documentationSteps;
      })
    } else {
      this.submitText = 'Popełniono błąd w strukturze jsonu scenariusza';
    }
  }
}
