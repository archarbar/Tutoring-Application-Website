import React from 'react';
import { MuiThemeProvider, createMuiTheme } from '@material-ui/core/styles';

export class ThemeWrapperComponent extends React.Component {

  render() {
    const theme = createMuiTheme({
      palette: {
        primary: {
          main: '#3f51b5',
        },
        error: {
          main: '#3f51b5',
        }
      },
    });

    return (
      <MuiThemeProvider theme={theme}>
        <div>
          {this.props.children}
        </div>
      </MuiThemeProvider>
    )
  }
}

export default ThemeWrapperComponent;
