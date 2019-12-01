import {Component, OnInit} from '@angular/core';
import {Scenario} from "./scenario/model/app-scenario.model";

@Component({
  selector: 'app-root',
  template: `
      <div class="form-container">
          <div class="create-scenario-buttons-container">
              <div class="button">Stwórz własny scenariusz</div>
              <div class="button">Wgraj gotowy scenariusz</div>
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
      </div>
  `,
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  scenario: Scenario;
  createScenarioActive: boolean = false;
  uploadScenarioActive: boolean = false;

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
  }

  isJson(text: string): boolean {
    return /^[\],:{}\s]*$/.test(
      text.replace(/\\["\\\/bfnrtu]/g, '@')
        .replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']')
        .replace(/(?:^|:|,)(?:\s*\[)+/g, ''));
  }

  isJsonScenarioObject(text: string): boolean {
    if (this.isJson(text)) {
      let obj = JSON.parse(text);
      return <Scenario>obj !== undefined;
    } else {
      return false;
    }
  }
}
