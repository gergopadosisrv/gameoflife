/**
 * Created by gergopados on 2017. 02. 01..
 */
module gol {

    export interface GameOfLifeControllerScope extends ng.IScope {
        dimension: Dimension;
        toggle: (row: number, col: number) => void;
        init: () => void;
        next: () => void;

        autoPlay: () => void;
        isAutoPlayInProgress: boolean;

        save: () => void;
        saveAs: () => void;
        patterns: BoardDto[];

        selectedBoard: BoardDto;
        selectPattern: (board: BoardDto) => void;
    }

    export class GameOfLifeController {

        private $inject = ['$scope', 'GameOfLifeService', '$interval'];

        private stopInterval: ng.IPromise<any>;

        constructor(public $scope: GameOfLifeControllerScope,
                    public GameOfLifeService: IGameOfLifeService,
                    public $interval: ng.IIntervalService) {
            $scope.dimension = new Dimension({
                width: 10,
                height: 10
            });

            $scope.toggle = (row, col) => {
                var current = $scope.selectedBoard.board[row][col];
                $scope.selectedBoard.board[row][col] = current == 0 ? 1 : 0;
            };

            $scope.init = () => {
                this.init();
            };

            $scope.next = () => {
                GameOfLifeService.getNextGeneration($scope.selectedBoard).then((nextGen)=> {
                    this.setBoard(nextGen.data);
                });
            }

            $scope.autoPlay = ()=> {
                if (!$scope.isAutoPlayInProgress) {
                    this.stopInterval = $interval($scope.next, 1000);
                }
                else {
                    $interval.cancel(this.stopInterval);
                }

                $scope.isAutoPlayInProgress = !$scope.isAutoPlayInProgress;

            };

            $scope.selectPattern = (board: BoardDto) => {
                this.setBoard(board);
            };

            $scope.save = ()=> {
                if ($scope.selectedBoard.name == '' || $scope.selectedBoard.name == null) {
                    alert('name is mandatory');
                    return;
                }
                GameOfLifeService.savePattern($scope.selectedBoard).then((id)=> {
                    this.$scope.selectedBoard.id = id.data;
                    this.getAllPatterns();
                });
            };

            $scope.saveAs = ()=> {
                $scope.selectedBoard.id = 0;
                $scope.save();
            }

            this.init();
            this.getAllPatterns();
        }

        private init() {
            this.$scope.selectedBoard = new BoardDto();
            this.$scope.selectedBoard.board = [];

            for (var h = 0; h < this.$scope.dimension.height; h++) {
                var row = [];
                for (var w = 0; w < this.$scope.dimension.width; w++) {
                    row.push(0);
                }
                this.$scope.selectedBoard.board.push(row);
            }
        }

        private setBoard(board: BoardDto) {
            this.$scope.selectedBoard = angular.copy(board);

            this.$scope.dimension.width = this.$scope.selectedBoard.board.length;
            this.$scope.dimension.height = this.$scope.selectedBoard.board[0].length;
        }

        private getAllPatterns() {
            this.GameOfLifeService.getAllPattern().then((patterns) => {
                this.$scope.patterns = patterns.data;
            });
        }
    }


    angular.module('gol').controller('GameOfLifeController', GameOfLifeController);
}