import {Component, OnInit} from '@angular/core';
import {Scenario, Step} from "./scenario/model/app-scenario.model";
import {KeywordsResponse, ScenarioService} from "./scenario/scenario.service";

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
            <p>title</p>
          </div>
          <input class="input" [(ngModel)]="scenario.title">
        </div>
        <row-adder-string-row [(list)]="scenario.actors" label="actor"></row-adder-string-row>
        <row-adder-string-row [(list)]="scenario.systemActors" label="system actor"></row-adder-string-row>
        <row-adder-step-row [(steps)]="scenario.steps"></row-adder-step-row>
      </div>
      <div class="upload-scenario" *ngIf="uploadScenarioActive">
        <textarea type="text" class="upload-input" [(ngModel)]="uploadedScenarioInput"></textarea>
      </div>
      <div class="submit-container" *ngIf="uploadScenarioActive || createScenarioActive">
        <div class="submit-buttons-container">
          <div class="button" (click)="submit()">Wyślij</div>
          <div class="button" (click)="resetForm()">Zresetuj scenariusz</div>
        </div>
        <p>{{submitErrorMessage}}</p>
      </div>
    </div>
    <div class="response" *ngIf="numberOfSteps">Liczba kroków wynosi {{numberOfSteps}}</div>
    <div class="response" *ngIf="!!this.keywordsResponse">Liczba słów kluczowych
      wynosi {{keywordsResponse.keywords}}</div>
    <div *ngIf="stepsWithoutActors" class="steps-without-actors response">
      <p style="font-weight: bold">Kroki bez aktora:</p>
      <div class="step-without-actor" *ngFor="let step of stepsWithoutActors">
        {{step}}
      </div>
    </div>
    <div *ngIf="documentationSteps" class="steps-without-actors response">
      <p style="font-weight: bold">Ponumerowane kroki:</p>
      <div class="step-without-actor" *ngFor="let step of documentationSteps">
        {{step}}
      </div>
    </div>
    <div class="flatten-scenario-container" *ngIf="scenarioSubmitted">
      <div class="choose-level-container">
        <div class="button" (click)="flattenScenario()">Pokaż scenariusz do wybranego poziomu</div>
        <div class="combo-container">
          <select (change)="level = $event.target.value">
            <option value="3">3</option>
            <option value="2">2</option>
            <option value="1">1</option>
          </select>
        </div>
      </div>
      <pre *ngIf="flattenScenarioSteps">
        {{getFlattenScenario()}}
      </pre>
    </div>
  `,
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  scenario: Scenario;
  createScenarioActive: boolean = false;
  uploadScenarioActive: boolean = false;
  uploadedScenarioInput: string;
  submitErrorMessage: string = '';
  numberOfSteps: number;
  keywordsResponse: KeywordsResponse;
  stepsWithoutActors: string [];
  documentationSteps: string [];
  flattenScenarioSteps: Step [];
  scenarioSubmitted: boolean = false;
  level: number = 3;

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

  getFlattenScenario(): string {
    return JSON.stringify(this.flattenScenarioSteps, null, 2);
  }

  resetForm(): void {
    this.scenario.title = '';
    this.scenario.actors = [];
    this.scenario.systemActors = [];
    this.scenario.steps = [];
    this.uploadedScenarioInput = '';
    this.submitErrorMessage = '';
    this.scenarioSubmitted = false;
    this.numberOfSteps = undefined;
    this.keywordsResponse = undefined;
    this.stepsWithoutActors = undefined;
    this.documentationSteps = undefined;
    this.flattenScenarioSteps = undefined;
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

  flattenScenario(): void {
    this.scenarioService.getScenariosAtProvidedLevel(this.scenario, this.level)
      .subscribe(flattenScenarioResponse => {
        this.flattenScenarioSteps = flattenScenarioResponse.steps;
      });
  }

  submit(): void {
    if (this.isJsonScenarioObject(this.uploadedScenarioInput) || this.createScenarioActive) {

      this.scenarioService.getSteps(this.scenario).subscribe(steps => {
        this.numberOfSteps = steps.steps;
      });

      this.scenarioService.getKeywordsAmount(this.scenario).subscribe(keywords => {
        this.keywordsResponse = keywords;
      });

      this.scenarioService.getStepsWithoutActor(this.scenario).subscribe(steps => {
        this.stepsWithoutActors = steps.stepsWithoutActors;
      });

      this.scenarioService.getPrettyPrintedScenario(this.scenario).subscribe(documentatnion => {
        this.documentationSteps = documentatnion.documentationSteps;
      });

      this.scenarioSubmitted = true;
    } else {
      this.submitErrorMessage = 'Popełniono błąd w strukturze jsonu scenariusza';
    }
  }
}
